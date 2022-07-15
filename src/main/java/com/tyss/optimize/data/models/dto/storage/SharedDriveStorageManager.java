package com.tyss.optimize.data.models.dto.storage;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.dto.FileMapperDTO;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import jcifs.CIFSContext;
import jcifs.CIFSException;
import jcifs.config.PropertyConfiguration;
import jcifs.context.BaseContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Component("sharedDrive")
@Slf4j
public class SharedDriveStorageManager implements StorageManager {

    @Autowired
    StorageConfigService storageConfigService;

    private SmbFile smbFile = null;

    @Override
    public void createObjectAndSave(StorageInfo storageInfo, List<MultipartFile> file) {
        String path = storageInfo.getFilePath().get(0);
        for (MultipartFile multipartFile : file) {
            String folderPath = "//" + storageInfo.getSharedIpAddress() + "/" + path;
            File sharedFile = new File(folderPath);
            if (!sharedFile.isDirectory() && !sharedFile.exists()) {
                Path sharedPath = Paths.get(folderPath);
                try {
                    Files.createDirectories(sharedPath);
                } catch (IOException e) {
                    log.error("Unable to create directories" + e);
                }
            }
            if (!multipartFile.isEmpty()) {
                String newFilePath = folderPath + multipartFile.getOriginalFilename();
                try {
                    Files.copy(multipartFile.getInputStream(), Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    log.error("Unable to copy files" + e);
                }
            }
        }
    }

    @Override
    public void createObjectAndSave(List<StorageInfo> storageInfoList) {
        for (StorageInfo storageInfo : storageInfoList) {
            Path path = Paths.get(storageInfo.getFilePath().get(0));
            String folderPath = "//" + storageInfo.getSharedIpAddress() + "/" + path.getParent();
            log.info("folder path: " + folderPath + ", full path: " + path);
            File sharedFile = new File(folderPath);
            if (!sharedFile.isDirectory() && !sharedFile.exists()) {
                Path sharedPath = Paths.get(folderPath);
                try {
                    Files.createDirectories(sharedPath);
                } catch (IOException e) {
                    log.error("SharedDrive: Unable to create directories" + e);
                }
            }
            if (Objects.nonNull(storageInfo.getInputStream())) {
                try {
                    Files.copy(storageInfo.getInputStream(), Paths.get(path.toString()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    log.error("SharedDrive: Unable to copy files" + e);
                }
            }
        }
    }

    @Override
    public ResponseDTO saveObject(StorageInfo storageInfo) {

        ResponseDTO responseDTO = new ResponseDTO();
        createConnectionForSmb(storageInfo, storageInfo.getSourceFilePath());
        try {
            for (FileMapperDTO mapperDTO:storageInfo.getFileMappers()) {
                try {
                    MultipartFile multipartFile = mapperDTO.getFile();
                    String folderPath = "//" + storageInfo.getSharedIpAddress() + "/"
                            + storageInfo.getSharedFolder() + "/" + mapperDTO.getLocation();
                    File sharedFile = new File(folderPath);
                    if (!sharedFile.isDirectory() && !sharedFile.exists()) {
                        Path sharedPath = Paths.get(folderPath);
                        Files.createDirectories(sharedPath);
                    }
                    if (!multipartFile.isEmpty()) {
                        String newFilePath = folderPath + multipartFile.getOriginalFilename();
                        Files.copy(multipartFile.getInputStream(), Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                catch (Exception e) {
                    responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
                    responseDTO.setStatus(CommonConstants.FAILURE);
                    continue;
                }
            }
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setMessage(CommonConstants.SUCCESS);
            return responseDTO;

        } catch (Exception e) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setStatus(CommonConstants.FAILURE);
            return responseDTO;
        }

    }

    @Override
    public long getFileSizeOfS3(StorageInfo storageInfo) {
        return 0;
    }

    @Override
    public ResponseEntity downloadFile(StorageInfo storageInfo) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ResponseEntity<Resource> fileResource = null;
        if (checkIfObjectExist(storageInfo)) {
            InputStream inputStream = smbFile.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1048576];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            fileResource =  ResponseEntity.ok()
                    .headers(header)
                    .contentLength(outputStream.size())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }else
            fileResource = ResponseEntity.notFound().build();
            return fileResource;
    }


    @Override
    public InputStream getObject(StorageInfo storageInfo, String filePath) {
        createConnectionForSmb(storageInfo, filePath);
        InputStream inputStream = null;
        try {
            inputStream = smbFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }



    @Override
    public InputStream getObjectStream(StorageInfo storageInfo, String filePath) {
        createConnectionForSmb(storageInfo, filePath);
        InputStream inputStream = null;
        try {
            inputStream = smbFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    @Override
    public List<InputStream> getObjects(StorageInfo storageInfo) {
        List<InputStream> inputStreams = new ArrayList<>();
        storageInfo.getFilePath().parallelStream().forEach(path -> {
            createConnectionForSmb(storageInfo, path);
            try {
                if (smbFile.exists() && smbFile != null) {
                    for (SmbFile file : smbFile.listFiles()) {
                        if (file.exists()) {
                            inputStreams.add(file.getInputStream());
                        }
                    }
                }
            } catch (SmbException e) {
                log.error("Smb Exception" + e);
            } catch (IOException e) {
                log.error("IO Exception" + e);
            }
        });
        return inputStreams;
    }

    @Override
    public boolean getObjectsAndSave(StorageInfo storageInfo) {
        storageInfo.getFilePath().parallelStream().forEach(path -> {
            createConnectionForSmb(storageInfo, path);
            try {
                if (smbFile.exists() && smbFile != null) {
                    try {
                        for (SmbFile file : smbFile.listFiles()) {
                            if (file.exists()) {
                                File folder = new File(storageInfo.getDestPath());
                                if (!folder.exists()) {
                                    folder.mkdir();
                                }
                                saveFiles(storageInfo.getDestPath(), file);
                                storageInfo.setStatus(true);
                            }
                        }
                    } catch (IOException e) {
                        log.error("Expected file doesn't exists" + e);
                    }
                }
            } catch (SmbException e) {
                log.error("Unable to connect to SmbFile" + e);
            }
        });
        return storageInfo.isStatus();
    }

    private void saveFiles(String folderPath, SmbFile file) throws IOException {
        if (folderPath != null) {
            File folder = new File(folderPath + file.getName());
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, folder.toPath());
        }
    }

    @Override
    public boolean deleteObject(StorageInfo storageInfo) {
        storageInfo.getFilePath().stream().forEach(path -> {
            createConnectionForSmb(storageInfo, path);
            try {
                    if (smbFile != null) {
                        if(smbFile.isDirectory()) {
                            for (SmbFile file : smbFile.listFiles()) {
                                file.delete();
                            }
                        }
                        smbFile.delete();
                        storageInfo.setStatus(true);
                    }

            } catch (SmbException e) {
                log.error("Cannot find the specified file" + e);
            }
        });
        return storageInfo.isStatus();
    }

    @Override
    public boolean checkIfObjectExist(StorageInfo storageInfo) throws SmbException {
        String path = storageInfo.getFilePath().get(0);
        createConnectionForSmb(storageInfo, path);
        return smbFile.exists();
    }

    private void createConnectionForSmb(StorageInfo storageInfo, String filePath) {
        PropertyConfiguration configuration = null;
        try {
            StorageConfig storageConfig = storageConfigService.getStorageConfig();
            String storagePath = "smb://" + storageInfo.getSharedIpAddress() + "/" + filePath;
            Properties properties = new Properties();
            configuration = new PropertyConfiguration(properties);
            NtlmPasswordAuthenticator auth = new NtlmPasswordAuthenticator(storageConfig.getDomain(), storageConfig.getUsername(),
                    storageConfig.getPassword());
            CIFSContext cif = new BaseContext(configuration).withCredentials(auth);
            smbFile = new SmbFile(storagePath, cif);
        } catch (MalformedURLException | CIFSException e) {
            log.error("MalformedURLException or CIFSException" + e);
        } catch (Exception e) {
            log.error("Unable to retrieve data from StorageConfig" + e);
        }
    }
}

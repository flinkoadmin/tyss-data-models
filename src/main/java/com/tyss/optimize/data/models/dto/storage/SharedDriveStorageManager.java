package com.tyss.optimize.data.models.dto.storage;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.db.model.License;
import com.tyss.optimize.data.models.dto.FileMapperDTO;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import jcifs.CIFSException;
import jcifs.smb.SmbFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.smb.session.SmbSession;
import org.springframework.integration.smb.session.SmbSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("sharedDrive")
@Slf4j
public class SharedDriveStorageManager implements StorageManager {

    @Autowired
    @Qualifier("smbSessionFactoryOld")
    private SmbSessionFactory smbSessionFactory;

    @Override
    public void createObjectAndSave(StorageInfo storageInfo, List<MultipartFile> file)  {
        String path = storageInfo.getFilePath().get(0);
        SmbSession smbSession = null;
        try {
            smbSession = smbSessionFactory.getSession();
            for (MultipartFile multipartFile : file) {
                String newFilePath = path + multipartFile.getOriginalFilename();
                smbSession.write(multipartFile.getInputStream(), newFilePath);
            }
        } catch (Exception e) {
            log.error("Exception while uploading to shared drive", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
    }

    @Override
    public void createObjectAndSave(List<StorageInfo> storageInfoList)  {
        SmbSession smbSession = null;
        try {
            smbSession = smbSessionFactory.getSession();
            for (StorageInfo storageInfo : storageInfoList) {
                Path path = Paths.get(storageInfo.getFilePath().get(0));
                InputStream inputStream = storageInfo.getInputStream();
                if (Objects.nonNull(inputStream)) {
                    smbSession.write(inputStream, path.toString());
                }
            }
        } catch (Exception e) {
            log.error("Exception while copying the files to shared drive", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
    }

    @Override
    public ResponseDTO saveObject(StorageInfo storageInfo) {
        SmbSession smbSession = null;
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            smbSession = smbSessionFactory.getSession();
            for (FileMapperDTO mapperDTO:storageInfo.getFileMappers()) {
                MultipartFile multipartFile = mapperDTO.getFile();
                String folderPath = storageInfo.getSharedFolder() + "/" + mapperDTO.getLocation();
                if (!multipartFile.isEmpty()) {
                    String newFilePath = folderPath + multipartFile.getOriginalFilename();
                    smbSession.write(multipartFile.getInputStream(), newFilePath);
                }
            }
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setMessage(CommonConstants.SUCCESS);
            return responseDTO;

        } catch (Exception e) {
            log.error("Exception while copying the files to shared drive", e);
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setStatus(CommonConstants.FAILURE);
            return responseDTO;
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
    }

    @Override
    public long getFileSize(StorageInfo storageInfo) {
        SmbSession smbSession = null;
        long bytes = 0;
        SmbFile file = null;
        try {
            smbSession = smbSessionFactory.getSession();
            file = smbSession.createSmbFileObject(storageInfo.getFilePath().get(0));
            bytes = file.length();

        } catch (Exception e) {
            log.error("Exception while getting file size", e);
        } finally {
            if (file != null) {
                file.close();
            }
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return bytes;
    }

    @Override
    public long getFolderSize(StorageInfo storageInfo) {
        SmbSession smbSession = null;
        long bytes = 0;
        try {
            smbSession = smbSessionFactory.getSession();
            String sourceFolderPath = storageInfo.getSourceFilePath();
            SmbFile[] smbFiles = smbSession.list(sourceFolderPath);
            for (SmbFile file : smbFiles) {
                if (file.isDirectory()) {
                    storageInfo.setSourceFilePath(file.getPath());
                    bytes += getFolderSize(storageInfo);
                } else {
                    bytes += file.length();
                }
            }
        } catch (Exception e) {
            log.error("Exception while getting folder size", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return bytes;
    }

    @Override
    public boolean copyFile(String from, String sourceFilePath, String type, String destinationFilePath, boolean createConnection) {
        SmbSession smbSession = null;
        try {
            smbSession = smbSessionFactory.getSession();
            SmbFile smbFile = smbSession.createSmbFileObject(sourceFilePath);
            SmbFile destinationFile = smbSession.createSmbFileObject(destinationFilePath);
            smbFile.copyTo(destinationFile);
            return true;
        } catch (MalformedURLException | CIFSException e) {
            log.error("MalformedURLException or CIFSException", e);
        } catch (Exception e) {
            log.error("Unable to retrieve data from StorageConfig", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return false;
    }

    @Override
    public boolean copyAll(String sourceBucket, String sourceFolderPath, String destinationBucket, String destinationFolderPath) {
        return copyFile(sourceBucket, sourceFolderPath, destinationBucket, destinationFolderPath, true);
    }

    @Override
    public ResponseEntity downloadFile(StorageInfo storageInfo) throws Exception {
        SmbSession smbSession = null;
        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ResponseEntity<Resource> fileResource = null;
        try {
            smbSession = smbSessionFactory.getSession();
            SmbFile smbFile = smbSession.exists(storageInfo.getFilePath().get(0)) ? smbSession.createSmbFileObject(storageInfo.getFilePath().get(0)) : null;
            if (smbFile != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                smbSession.read(storageInfo.getFilePath().get(0), outputStream);
                ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
                fileResource = ResponseEntity.ok()
                        .headers(header)
                        .contentLength(outputStream.size())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(resource);
            } else {
                fileResource = ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Exception downloading the smb file to local", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return fileResource;
    }


    @Override
    public boolean checkIfObjectExistsInOffline(StorageInfo storageInfo, License license) {
        return  checkIfObjectExist(storageInfo);
    }

    @Override
    public String generateTemporaryUrlForDownload(String bucketName, String path, int expiryTimeInMillis) {
        return path;
    }

    @Override
    public String generateTemporaryUrlForUpload(String bucketName, String path, int expiryTimeInMillis) {
        return path;
    }
    public List<String> getAllFilePath(String baseFilePath, String wildcard) throws Exception {
        baseFilePath = baseFilePath.replace("//", "/").trim();
        SmbSession smbSession = null;
        List<String> allFilePath = new ArrayList<>();
        try {
            smbSession = smbSessionFactory.getSession();
            SmbFile[] smbFiles = smbSession.list(baseFilePath);
            createFilePathsForWildCard(smbFiles, smbSession, allFilePath, wildcard);
        } catch (Exception e) {
            log.error("Unable to create connection", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return allFilePath;
    }

        private void createFilePathsForWildCard(SmbFile[] smbFiles, SmbSession smbSession, List<String> allFilePaths, String wildcard) throws IOException {
            for (SmbFile smbFile : smbFiles) {
                String updatedPath = null;
                if(smbFile.isDirectory()){
                     updatedPath = smbFile.getPath().substring(smbFile.getPath().lastIndexOf("License"));
                    createFilePathsForWildCard(smbSession.list(updatedPath), smbSession, allFilePaths, wildcard);
                }
                if (smbFile.isDirectory() && smbFile.getPath().contains(wildcard)) {
                        allFilePaths.add(updatedPath);
                    }
                }
        }


    @Override
    public InputStream getObject(StorageInfo storageInfo, String filePath) {
        SmbSession smbSession = null;
        try {
            smbSession = smbSessionFactory.getSession();
            return smbSession.readRaw(filePath);
        } catch (Exception e) {
            log.error("Exception getting file from shared drive", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return null;
    }


    @Override
    public InputStream getObjectStream(StorageInfo storageInfo, String filePath) {
        return getObject(storageInfo, filePath);
    }

    @Override
    public List<InputStream> getObjects(StorageInfo storageInfo) {
        SmbSession smbSession = smbSessionFactory.getSession();
        List<InputStream> inputStreams = new ArrayList<>();
        storageInfo.getFilePath().stream().forEach(path -> {
            try {
                inputStreams.add(smbSession.readRaw(path));
            } catch (Exception e) {
                log.error("Exception while downloading the file",  e);
            }
        });
        if (smbSession.isOpen()) {
            smbSession.close();
        }
        return inputStreams;
    }

    @Override
    public boolean getObjectsAndSave(StorageInfo storageInfo) {
        SmbSession smbSession = smbSessionFactory.getSession();
        storageInfo.getFilePath().parallelStream().forEach(path -> {
            try {
                SmbFile smbFile = smbSession.createSmbFileObject(path);
                if (smbFile != null) {
                    File folder = new File(storageInfo.getDestPath());
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    downloadFile(storageInfo.getDestPath(), smbFile);
                    storageInfo.setStatus(true);
                }
            } catch (Exception e) {
                log.error("Exception while downloading the smb file contents", e);
            }
        });
        if (smbSession.isOpen()) {
            smbSession.close();
        }
        return storageInfo.isStatus();
    }

    private void downloadFile(String folderPath, SmbFile file) throws IOException {
        if (folderPath != null) {
            File folder = new File(folderPath + file.getName());
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, folder.toPath());
            IOUtils.closeQuietly(inputStream);
            file.close();
        }
    }

    @Override
    public boolean deleteObject(StorageInfo storageInfo) {
        SmbSession smbSession = smbSessionFactory.getSession();
        storageInfo.getFilePath().stream().forEach(path -> {
            try {
                smbSession.remove(path);
                storageInfo.setStatus(true);
            } catch (Exception e) {
                log.error("Exception while deleting the specified file", e);
            }
        });
        if (smbSession.isOpen()) {
            smbSession.close();
        }
        return storageInfo.isStatus();
    }

    @Override
    public boolean offlineDeleteObject(StorageInfo storageInfo, License license) {
        return  deleteObject(storageInfo);
    }


    @Override
    public boolean checkIfObjectExist(StorageInfo storageInfo) {
        SmbSession smbSession = null;
        try {
            smbSession = smbSessionFactory.getSession();
            String path = storageInfo.getFilePath().get(0);
            return smbSession.exists(path);
        } catch (Exception e) {
            log.error("Exception checking whether object exists", e);
        } finally {
            if (smbSession != null && smbSession.isOpen()) {
                smbSession.close();
            }
        }
        return false;
    }
}

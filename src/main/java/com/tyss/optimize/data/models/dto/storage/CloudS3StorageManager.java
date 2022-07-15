package com.tyss.optimize.data.models.dto.storage;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.common.util.MimeTypeUtils;
import com.tyss.optimize.data.models.dto.FileMapperDTO;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
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
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component("cloudS3")
@Slf4j
public class CloudS3StorageManager implements StorageManager {

    private AmazonS3 s3Client = null;

    private S3Client s3 = null;

    @Autowired
    StorageConfigService storageConfigService;

    @Override
    public void createObjectAndSave(StorageInfo storageInfo, List<MultipartFile> file) throws AmazonS3Exception, IOException {
        createConnectionForS3();
        for (MultipartFile multipartFile : file) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());
            String path = storageInfo.getFilePath().get(0);
            s3Client.putObject(storageInfo.getBucketName(), path + multipartFile.getOriginalFilename(), multipartFile.getInputStream(), metadata);
        }
    }

    @Override
    /**
     * This function create and save on cloudS3 by taking params input streams
     */
    public void createObjectAndSave(List<StorageInfo> storageInfoList) throws IOException {
        createConnectionForS3();
        for (StorageInfo storageInfo : storageInfoList) {
            ObjectMetadata metadata = new ObjectMetadata();
            String fullPath = storageInfo.getFilePath().get(0);
            metadata.setContentType(MimeTypeUtils.getContentTypeByFileName(fullPath));
            byte[] bytes = IOUtils.toByteArray(storageInfo.getInputStream());
            metadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            s3Client.putObject(storageInfo.getBucketName(), fullPath, byteArrayInputStream, metadata);
        }
    }

    @Override
    public ResponseDTO saveObject(StorageInfo storageInfo) {
        ResponseDTO responseDTO = new ResponseDTO();
        createConnectionForS3();
        try {
            List<FileMapperDTO> fileMappers = storageInfo.getFileMappers();
            for (FileMapperDTO fileMapperDTO : fileMappers) {
                ObjectMetadata metadata = new ObjectMetadata();
                MultipartFile file = fileMapperDTO.getFile();
                metadata.setContentType(MimeTypeUtils.getContentTypeByFileName(fileMapperDTO.getLocation() + "/" + file.getName()));
                metadata.setContentLength(file.getSize());
                s3Client.putObject(storageInfo.getBucketName(), fileMapperDTO.getLocation() + "/" + file.getName(), file.getInputStream(), metadata);
            }
            responseDTO.setResponseCode(HttpStatus.OK.value());
            responseDTO.setMessage(CommonConstants.SUCCESS);

        } catch (IOException e) {
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
            responseDTO.setStatus(CommonConstants.FAILURE);
        }
        return responseDTO;
    }

    @Override
    public ResponseEntity downloadFile(StorageInfo storageInfo) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ResponseEntity<Resource> fileResource = null;
        if (checkIfObjectExist(storageInfo)) {
            String filePath = storageInfo.getFilePath().get(0);
            S3Object s3object = s3Client.getObject(storageInfo.getBucketName(), filePath);
            InputStream inputStream = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1048576];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            fileResource = ResponseEntity.ok()
                    .headers(header)
                    .contentLength(outputStream.size())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } else
            fileResource = ResponseEntity.notFound().build();
        return fileResource;
    }


    @Override
    public InputStream getObjectStream(StorageInfo storageInfo, String filePath) {
        createConnectionForS3();
        InputStream inputStream = null;
        try {
            S3Object s3object = s3Client.getObject(storageInfo.getBucketName(), filePath);
            return s3object.getObjectContent();
        } catch (Exception e) {
            System.out.println(inputStream);
            log.error("Something went wrong");
            return inputStream;
        }
    }

    @Override
    public InputStream getObject(StorageInfo storageInfo, String filePath) {
        createConnectionForS3();
        S3Object s3object = s3Client.getObject(storageInfo.getInputs().getBucketName(), filePath);
        InputStream inputStream = s3object.getObjectContent();
        return inputStream;
    }


    @Override
    public List<InputStream> getObjects(StorageInfo storageInfo) {
        ResponseDTO responseDTO = new ResponseDTO();
        createConnectionForS3();
        List<InputStream> inputStreams = new ArrayList<>();
        for (String filePath : storageInfo.getFilePath()) {
            if (s3Client.doesBucketExist(storageInfo.getBucketName())) {
                ObjectListing listing = s3Client.listObjects(storageInfo.getBucketName(), filePath);
                for (S3ObjectSummary summary : listing.getObjectSummaries()) {
                    if (summary.getKey() != null) {
                        final GetObjectRequest getObjectRequest = new GetObjectRequest(
                                summary.getBucketName(),
                                summary.getKey());
                        final S3Object s3Object = s3Client.getObject(getObjectRequest);
                        inputStreams.add(s3Object.getObjectContent());
                    }
                }
                responseDTO.setResponseObject(inputStreams);
            } else {
                throw new NoSuchElementException("Such type of bucket is not exist ");
//            responseDTO.setMessage("such type of bucket is not exist");
//            responseDTO.setResponseCode(HttpStatus.NO_CONTENT.value());
//            s3Client.createBucket(storageInfo.getBucketName());
            }
        }
        return inputStreams;
    }

    @Override
    public boolean getObjectsAndSave(StorageInfo storageInfo) {
        createConnectionForS3();
        if (s3Client.doesBucketExist(storageInfo.getBucketName())) {
            if (storageInfo.getFilePath() != null) {
                storageInfo.getFilePath().parallelStream().forEach(path ->
                        {
                            ObjectListing listing = s3Client.listObjects(storageInfo.getBucketName(), path);
                            if (listing.getObjectSummaries().size() > 1) {
                                for (S3ObjectSummary summary : listing.getObjectSummaries()) {
                                    try {
                                        if (summary.getKey() != null) {
                                            String[] filepath = summary.getKey().split("/");
                                            String fileName = filepath[filepath.length - 1];
                                            if (fileName != null && fileName.contains(".")) {
                                                File folder = new File(storageInfo.getDestPath());
                                                if (!folder.exists()) {
                                                    folder.mkdir();
                                                }
                                                saveFiles(storageInfo, fileName, summary);
                                                storageInfo.setStatus(true);
                                            }
                                        }
                                    } catch (IOException e) {
                                        log.error("File already exists " + e);
                                    }
                                }
                            } else {
                                log.error("Expected file doesn't exists ");
                            }
                        }
                );
            }
        }
        return storageInfo.isStatus();
    }

    private void saveFiles(StorageInfo storageInfo, String fileName, S3ObjectSummary summary) throws IOException {
        if (storageInfo.getDestPath() != null) {
            File folder = new File(storageInfo.getDestPath() + fileName);
            S3Object s3Object = s3Client.getObject(storageInfo.getBucketName(), summary.getKey());
            Files.copy(s3Object.getObjectContent(), folder.toPath());
        }
    }

    @Override
    public boolean deleteObject(StorageInfo storageInfo) {
        createConnectionForS3();
        if (s3Client.doesBucketExist(storageInfo.getBucketName())) {
            if (storageInfo.getFilePath() != null) {
                storageInfo.getFilePath().parallelStream().forEach(path ->
                {
                    try {
                        ObjectListing listing = s3Client.listObjects(storageInfo.getBucketName(), path);
                        DeleteObjectsRequest req = new DeleteObjectsRequest(storageInfo.getBucketName());
                        List<DeleteObjectsRequest.KeyVersion> keys = new ArrayList<>(listing.getObjectSummaries().size());
                        for (S3ObjectSummary summary : listing.getObjectSummaries()) {
                            keys.add(new DeleteObjectsRequest.KeyVersion(summary.getKey()));
                        }
                        req.withKeys(keys);
                        s3Client.deleteObjects(req);
                        storageInfo.setStatus(true);
                    } catch (AmazonS3Exception e) {
                        log.error("File doesn't exists " + e);
                    }
                });
            }
        }
        return storageInfo.isStatus();
    }

    @Override
    public boolean checkIfObjectExist(StorageInfo storageInfo) {
        createConnectionForS3();
        String path = storageInfo.getFilePath().get(0);
        return s3Client.doesObjectExist(storageInfo.getBucketName(), path);
    }

    public ListObjectsV2Iterable getIterableObjectFromCloud(String bucketName, String prefix, StorageConfig storageInfo) {
        createConnectionForV2(storageInfo);
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).prefix(prefix).build();
        return s3.listObjectsV2Paginator(request);
    }

    @Override
    public long getFileSizeOfS3(StorageInfo storageInfo) {
        createConnectionForS3();
        return s3Client.getObjectMetadata(storageInfo.getBucketName(), storageInfo.getFilePath().get(0)).getContentLength();
    }

    private void createConnectionForS3() {
        log.info("Establishing Connection");
        StorageConfig storageConfig = null;
        try {
            storageConfig = storageConfigService.getStorageConfig();
        } catch (Exception e) {
            log.error("Unable to retrieve data from StorageConfig " + e);
        }
        AWSCredentials credentials = new BasicAWSCredentials(storageConfig.getAccessKey(), storageConfig.getSecretKey());
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
        log.info("Connection Established");
    }

    public void createConnectionForV2(StorageConfig storageInfo) {
        log.info("Establishing Connection");
        StorageConfig storageConfig = null;
        try {
            storageConfig = storageConfigService.getStorageConfig(storageInfo);
        } catch (Exception e) {
            log.error("Unable to retrieve data from StorageConfig " + e);
        }
        AwsBasicCredentials credentials = AwsBasicCredentials.create(storageConfig.getAccessKey(), storageConfig.getSecretKey());
        Region region = Region.AP_SOUTH_1;
        s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
        log.info("Connection Established");
    }

}

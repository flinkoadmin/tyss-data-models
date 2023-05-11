package com.tyss.optimize.data.models.dto.storage;


import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
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
import com.tyss.optimize.data.models.db.model.License;
import com.tyss.optimize.data.models.dto.FileMapperDTO;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
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
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.*;

@Component("cloudS3")
@Slf4j
public class CloudS3StorageManager implements StorageManager {

    private AmazonS3 s3Client = null;

    private S3Client s3 = null;
    
    private S3Presigner s3Presigner = null;

    @Autowired
    StorageConfigService storageConfigService;

    @Override
    public void createObjectAndSave(StorageInfo storageInfo, List<MultipartFile> file) throws AmazonS3Exception, IOException {
        try{
            createConnectionForS3();
        } catch (Exception e){
            License license = new License();
            license.setId(storageInfo.getLicenseId());
            createConnectionForS3(license);
        }
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


    public boolean offlineDeleteObject(StorageInfo storageInfo, License license) {
        createConnectionForS3(license);
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


    public boolean checkIfObjectExistsInOffline(StorageInfo storageInfo, License license) throws Exception {
        createConnectionForS3(license);
        String path = storageInfo.getFilePath().get(0);
        return s3Client.doesObjectExist(storageInfo.getBucketName(), path);
    }

    @Override
    public List<String> getAllFilePath(String baseFilePath, String wildcard) throws Exception {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    @Override
    public InputStream getObjectStream(StorageInfo storageInfo, String filePath) {
        createConnectionForS3();
        InputStream inputStream = null;
        try {
            S3Object s3object = s3Client.getObject(storageInfo.getBucketName(), filePath);
            return s3object.getObjectContent();
        } catch (Exception e) {
            log.error("Something went wrong");
            return inputStream;
        }
    }

    @Override
    public InputStream getObject(StorageInfo storageInfo, String filePath) {
        try{
            createConnectionForS3();
        } catch (Exception e){
            License license = new License();
            license.setId(storageInfo.getLicenseId());
            createConnectionForS3(license);
        }
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
    @Override
    public boolean copyFile(String sourceBucket, String sourceFilePath, String destinationBucket, String destinationFilePath, boolean createConnection) {
        try {
            log.info("Copying the file in S3");
            if (createConnection) {
                createConnectionForS3();
            }
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(sourceBucket, sourceFilePath, destinationBucket, destinationFilePath);
            s3Client.copyObject(copyObjRequest);
            return true;
        } catch (SdkClientException ex) {
            log.error("Exception while copying object in S3", ex);
        }
        return false;
    }
	
    @Override
    public boolean copyAll(String sourceBucket, String sourceFolderPath, String destinationBucket, String destinationFolderPath) {
        log.info("Copying the files recursively in S3");
        try {
            createConnectionForS3();
            ListObjectsRequest request = new ListObjectsRequest().withBucketName(sourceBucket)
                    .withPrefix(sourceFolderPath);
            ObjectListing response = s3Client.listObjects(request);
            do {
                for (S3ObjectSummary objectSummary : response.getObjectSummaries()) {
                    CopyObjectRequest copyObjRequest = new CopyObjectRequest(sourceBucket, objectSummary.getKey(), destinationBucket, objectSummary.getKey().replace(sourceFolderPath, destinationFolderPath));
                    s3Client.copyObject(copyObjRequest);
                }
                response = s3Client.listNextBatchOfObjects(response);
            } while (response.isTruncated());
            return true;
        } catch (AmazonS3Exception e) {
            log.error("Exception while copying the files in S3", e);
        }
        return false;
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
                storageInfo.getFilePath().stream().forEach(path ->
                {
                    if(Objects.nonNull(path)){
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
                    }
                });
            }
        }
        return storageInfo.isStatus();
    }

    @Override
    public boolean checkIfObjectExist(StorageInfo storageInfo) {
        try{
            createConnectionForS3();
        } catch (Exception e){
            License license = new License();
            license.setId(storageInfo.getLicenseId());
            createConnectionForS3(license);
        }
        String path = storageInfo.getFilePath().get(0);
        return s3Client.doesObjectExist(storageInfo.getBucketName(), path);
    }

    public ListObjectsV2Iterable getIterableObjectFromCloud(String bucketName, String prefix, StorageConfig storageInfo) {
        createConnectionForV2(storageInfo);
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).prefix(prefix).build();
        return s3.listObjectsV2Paginator(request);
    }

    @Override
    public long getFileSize(StorageInfo storageInfo) {
        createConnectionForS3();
        return s3Client.getObjectMetadata(storageInfo.getBucketName(), storageInfo.getFilePath().get(0)).getContentLength();
    }

    @Override
    public long getFolderSize(StorageInfo storageInfo) {
        createConnectionForS3();
        long totalSize = 0;
        ListObjectsRequest request = new ListObjectsRequest().withBucketName(storageInfo.getBucketName())
                .withPrefix(storageInfo.getFilePath().get(0));
        ObjectListing response = s3Client.listObjects(request);
        do {
            for (S3ObjectSummary objectSummary : response.getObjectSummaries()) {
                totalSize += objectSummary.getSize();
            }
            response = s3Client.listNextBatchOfObjects(response);
        } while (response.isTruncated());
        return totalSize;
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

    private void createConnectionForS3V3() {
        log.info("Establishing Connection V3");
        StorageConfig storageConfig;
        AwsCredentialsProvider credentialsProvider = null;
        try {
            storageConfig = storageConfigService.getStorageConfig();
            final String accessKey = storageConfig.getAccessKey();
            final String secretKey = storageConfig.getSecretKey();
            credentialsProvider = () -> new AwsCredentials() {
                @Override
                public String accessKeyId() {
                    return accessKey;
                }

                @Override
                public String secretAccessKey() {
                    return secretKey;
                }
            };
        } catch (Exception e) {
            log.error("Unable to retrieve data from StorageConfig V3" + e);
        }
         s3Presigner = S3Presigner.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(credentialsProvider)
                .build();

        log.info("Connection Established V3");
    }

    private void createConnectionForS3(License license) {
        log.info("Establishing Connection");
        StorageConfig storageConfig = null;
        try {
            storageConfig = storageConfigService.getStorageConfig(license);
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

    @Override
    public String generateTemporaryUrlForDownload(String bucketName, String path, int expiryTimeInMillis) {
        createConnectionForS3();
        Date expiryDate = DateUtils.addMilliseconds(new Date(), expiryTimeInMillis);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, path)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiryDate);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();

    }
    @Override
    public String generateTemporaryUrlForUpload(String bucketName, String path, int expiryTimeInMillis) {
        createConnectionForS3V3();
        software.amazon.awssdk.services.s3.model.PutObjectRequest objectRequest = software.amazon.awssdk.services.s3.model.PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .contentType(MimeTypeUtils.getContentTypeByFileName(path))
                .build();
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMillis(expiryTimeInMillis))
                .putObjectRequest(objectRequest)
                .build();
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        URL url = presignedRequest.url();
        return url.toString();
    }
}

package com.tyss.optimize.data.models.dto.storage;


import com.tyss.optimize.data.models.db.model.License;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Component
public interface StorageManager {

    void createObjectAndSave(StorageInfo storageInfo, List<MultipartFile> file) throws Exception;

    void createObjectAndSave(List<StorageInfo> storageInfoList) throws Exception;

    boolean getObjectsAndSave(StorageInfo storageInfo);

    InputStream getObjectStream(StorageInfo storageInfo, String filePath);

    List<InputStream> getObjects(StorageInfo storageInfo);

    boolean checkIfObjectExist(StorageInfo storageInfo) throws Exception;

    boolean copyFile(String sourceBucket, String sourceFilePath, String destinationBucket, String destinationFilePath, boolean createConnection);

    boolean copyAll(String sourceBucket, String sourceFolderPath, String destinationBucket, String destinationFolderPath);

    boolean deleteObject(StorageInfo storageInfo);

    InputStream getObject(StorageInfo storageInfo, String filePath);

    ResponseDTO saveObject(StorageInfo storageInfo);

    long getFileSize(StorageInfo storageInfo);

    long getFolderSize(StorageInfo storageInfo);

    ResponseEntity downloadFile(StorageInfo storageInfo)throws Exception;

    boolean offlineDeleteObject(StorageInfo storageInfo, License license);

    boolean checkIfObjectExistsInOffline(StorageInfo storageInfo, License license) throws Exception;

    String generateTemporaryUrlForDownload(String bucketName, String path, int expiryTimeInMillis);

    String generateTemporaryUrlForUpload(String bucketName, String path, int expiryTimeInMillis);
    
    List<String> getAllFilePath(String baseFilePath, String wildcard) throws Exception;
}

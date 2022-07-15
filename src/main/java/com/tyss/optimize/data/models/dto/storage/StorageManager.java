package com.tyss.optimize.data.models.dto.storage;


import com.amazonaws.services.ec2.model.Storage;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
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

    boolean deleteObject(StorageInfo storageInfo);

    InputStream getObject(StorageInfo storageInfo, String filePath);

    ResponseDTO saveObject(StorageInfo storageInfo);

    long getFileSizeOfS3(StorageInfo storageInfo);

    ResponseEntity downloadFile(StorageInfo storageInfo)throws Exception;
}

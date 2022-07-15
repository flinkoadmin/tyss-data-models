package com.tyss.optimize.data.models.dto;

import lombok.Data;

@Data
public class JarInfo {

    private StorageInfo storageInfo;
    private String sourceFilePath;
    private String destFilePath;
}

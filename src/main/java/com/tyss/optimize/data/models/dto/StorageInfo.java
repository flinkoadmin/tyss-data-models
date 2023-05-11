package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StorageInfo {
    private String type;
    private StorageInput inputs;
    private StorageOutput outputs;
    private String installedDir;
    private String sourceFilePath;
    private String destPath;
    private boolean status;
    private boolean updateSrc;
    private String bucketName;
    private List<String> filePath;
    private String sharedIpAddress;
    private String sharedFolder;
    private List<FileMapperDTO> fileMappers;
    private InputStream inputStream;
    private String driverBucketName;
    private String licenseId;
}

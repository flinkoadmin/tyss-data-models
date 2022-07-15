package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.ClientSystemConfig;
import lombok.Data;

@Data
public class ExecutionClientDetails {
    private StorageInfo storageInfo;
    private String sourceFilePath;
    private String destFilePath;
    private String installedDir;
    private Execution execution;
    private String clientId;
    private ClientSystemConfig clientSystemConfig;
}

package com.tyss.optimize.data.models.dto;

import lombok.Data;

@Data
public class FileDTO {
    private String id;
    private String projectId;
    private String folderName;
    private String folderPath;
    private boolean duplicateStatus;
    private String name;
}

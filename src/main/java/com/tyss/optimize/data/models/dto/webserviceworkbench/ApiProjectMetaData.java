package com.tyss.optimize.data.models.dto.webserviceworkbench;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiProjectMetaData {
    private String projectId;
    private String moduleId;
    private String scriptId;
}

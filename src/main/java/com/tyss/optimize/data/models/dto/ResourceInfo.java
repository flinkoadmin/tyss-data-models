package com.tyss.optimize.data.models.dto;

import lombok.Data;

@Data
public class ResourceInfo {
    private String downloadLoc;
    private String bucketName;
    private String type;
    private boolean variablesPresent;
    private boolean projectElementsPresent;
    private boolean testDataPresent;
    private boolean filesPresent;
    private boolean programElementsPresent;
    private boolean stepGroupsPresent;
    private boolean scriptsPresent;
}

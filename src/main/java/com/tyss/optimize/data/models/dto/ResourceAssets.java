package com.tyss.optimize.data.models.dto;
import lombok.Data;

@Data
public class ResourceAssets {
    private String importAction;
    private ResourceResponse resourceResponse;
    private ResourceInfo resourceInfo;
    private String bucketName;
    private String type;
}

package com.tyss.optimize.data.models.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceUpdateEvent {
    private String type;
    private String id;
    private String parentId;
    private String projectId;
    private String licenseId;

    private Map<String, Object> metaData;

    public ReferenceUpdateEvent(String type, String id, String parentId, String projectId, String licenseId) {
        this.type = type;
        this.id = id;
        this.parentId = parentId;
        this.projectId = projectId;
        this.licenseId = licenseId;
    }
}

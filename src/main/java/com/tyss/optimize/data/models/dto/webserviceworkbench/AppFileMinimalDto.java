package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.tyss.optimize.data.models.db.model.AppFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppFileMinimalDto {
    private String id;
    private String folderId;
    private String name;
    private Boolean isEnabled = true;
    private Boolean isModified = false;
    private String type;
    private String value;
    private String url;

    public AppFileMinimalDto(AppFile appFile) {
        if (appFile != null) {
            this.id = appFile.getId();
            this.name = appFile.getName();
            this.folderId=appFile.getParentId();
        }
    }
}

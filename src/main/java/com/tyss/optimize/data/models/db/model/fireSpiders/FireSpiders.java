package com.tyss.optimize.data.models.db.model.fireSpiders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FireSpiders {

    private String userName;
    private String licenseId;
    private String licenseName;
    private String action;
    private String projectId;
    private String projectName;
    private String lastLoginTime;
    private List<Scripts> scripts;
}

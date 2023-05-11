package com.tyss.optimize.data.models.db.model.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.common.util.RoleTypes;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TabPermission {
    /*private String projectMenu = RoleTypes.NO_ACCESS;
    private String webServiceWorkbench = RoleTypes.NO_ACCESS;
    private String databaseWorkbench = RoleTypes.NO_ACCESS;
    */

    private String dashboard = RoleTypes.NO_ACCESS;
    private String analytics = RoleTypes.NO_ACCESS;
    private String repository = RoleTypes.NO_ACCESS;
    private String testData = RoleTypes.NO_ACCESS;
    private String testDevelopment = RoleTypes.NO_ACCESS;
    private String execution = RoleTypes.NO_ACCESS;
    private String defects = RoleTypes.NO_ACCESS;
    private String configuration = RoleTypes.NO_ACCESS;
}

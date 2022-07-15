package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.auth.TabPermission;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectPermission extends BaseEntity{
    private Project project;
    private TabPermission tabPermission;
}

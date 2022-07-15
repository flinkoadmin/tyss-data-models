package com.tyss.optimize.data.models.db.model.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(value = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ROLE";

    @Id
    public String id;

    @NotNull(message = "role name is mandatory")
    private String name;
    private String desc;
    private TabPermission tabPermissions;
    private String projectId;
}

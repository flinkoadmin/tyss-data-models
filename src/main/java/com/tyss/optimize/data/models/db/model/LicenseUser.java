package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseUser extends BaseEntity{
//    @Id
    private String userId;
    private String name;
    private String emailId;
    private String privilege;
    private String status;
    private List<Project> projects;
    private Phone phoneNumbers;
}

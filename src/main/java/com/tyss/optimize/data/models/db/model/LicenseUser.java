package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseUser {
//    @Id
    private String userId;
    private String name;
    private String emailId;
    private String privilege;
    private String modifiedBy;
    private String modifiedOn;
    private String status;
    private List<Project> projects;
}

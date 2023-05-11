package com.tyss.optimize.data.models.db.model.flink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlinkUser {
    private String name;
    private String emailId;
    private String password;
    private FlinkPhone phoneNumbers;
    private String privilege;
    private List<FlinkLicense> licenses;
    private String activationStatus;
    private String organisationName;
    private String jobTitle;
    private String defaultLicenseId;
    private String currentLicenseId;
    private String currentPrivilege;
    private String scope;
    private String id;
    private String userName;
    private String licenseId;
    private String jti;
    private String licenseStatus;
    private String licenseType;
}

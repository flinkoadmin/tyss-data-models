package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "license_privileges")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicensePrivilege extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "LICENSE_PRIVILEGE";

    @Id
    private String id;
    private String licenseType;
    private String licensePlan;
    private LicensePrivilegeInfo privilege;
    private String startDate;
    private String endDate;
    private boolean expired;
    private String licenseTypeDisplay;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "licenseReconciliation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseReconciliation {

    @Transient
    public static final String SEQUENCE_NAME = "LICENSE_RECONCILIATION";

    @Id
    public String id;
    private String licenseId;
    private List<String> types;
    private List<String> projectIds;
    private String oldLicensePlan;
    private String newLicensePlan;
    private String expiry;

}

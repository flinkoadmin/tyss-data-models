package com.tyss.optimize.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseReconciliationDTO {

    private String licenseId;
    private List<String> types;
    private List<String> projectIds;
    private String oldLicensePlan;
    private String newLicensePlan;
    private String expiry;
}

package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.LicensePrivilegeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicensePrivilegeDTO {

    private String licenseType;
    private String licensePlan;
    private LicensePrivilegeInfo privilege;
    private String startDate;
    private String endDate;
    private boolean expired;
}

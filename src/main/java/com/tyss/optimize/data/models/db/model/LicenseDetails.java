package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseDetails {
    @Id
    public String id;
//    private String licensePlanId;
    private String licensePlan;
    private String licenseType;
    private String licenseName;
    private String emailId;
    private String licenseStatus;
    private String billingCycle;
    private String numberOfParallelRuns;
    private String storage;
    private String licenseOwner;
    private PaymentDetails paymentDetails;
    private List<LicenseUser> licenseUsers;
    private String privilege;
    private String defaultProjectMemory;
    private Date startDate;
    private Date expiryDate;
    private String action;
}

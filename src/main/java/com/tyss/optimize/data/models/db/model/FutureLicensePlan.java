package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutureLicensePlan {
    private String licensePlan;
    private String licenseType;
    private String startDate;
    private String expiryDate;
    private String billingCycle;
    private String numberOfParallelRuns;
    private String storage;
    private String storageUnit;
    private String netTotal;
    private String tax;
    private String grandTotal;
    private String paymentStatus;
}

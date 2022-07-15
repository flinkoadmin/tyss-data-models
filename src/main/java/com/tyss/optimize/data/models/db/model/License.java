package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(value = "license")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class License extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "LICENSE";

	@Id
	public String id;
	private String customerLicenseId;
	private String orgId;
	private String licensePlanId;
	private String licensePlan;
	private String licenseType;
	private Integer numberOfMachines;
	private Integer numberOfUsers;
	private String purchasedMemory;
	private String emailId;
	private String startDate;
	private String expiryDate;
	private String paymentId;
	private PaymentDetails paymentDetails;
	private String licenseUsageId;
	private String billingCycle;
	private String licenseStatus;

	private List<LicenseUser> licenseUsers;
	private String numberOfParallelRuns;
	private String storage;
	private String licenseName;
	private String licenseOwner;
	private String privilege;
	private String defaultProjectMemory;

	private String organisationName;
	private String jobTitle;
	private String ownerPhoneNumber;
	private String remainingDays;
	private String action;
	private String costPerRun;
	private String costPerStorage;
	private FutureLicensePlan futureLicensePlan;
}

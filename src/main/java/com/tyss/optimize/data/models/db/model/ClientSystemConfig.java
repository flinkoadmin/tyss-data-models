package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.common.util.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "client_system_configs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientSystemConfig extends BaseEntity {
	
	@Transient
    public static final String SEQUENCE_NAME = "CLIENT_SYSTEM_CONFIG";
	
	@Id
    public String id;
	private String clientId;
	private String machineId;
    private String installedDir;
	private String port;
	private String status;
	private String executionStatus;
	private Boolean disabled;
	private String licenseId;
	private String userId;
	private MachineInfo machine;
	private List<DeviceInfo> systemConfigDetails;
	private List<String> externalPlugins;
	private String executionEnvironment;
	private String access;
	private String network;
	private String topic;
	private String clientPort;
	private List<Driver> drivers;
	private Boolean driverSynced;
	private Boolean isDriversNotMatched;
	private Boolean isDriverSyncFirstTimeSuccessfully;
	private String driverVersion;
	private String executionId;
	private String executionType;
	private String action;
	private ClientStatus clientStatus;
	private Boolean headless;
	private String uuidType;
}

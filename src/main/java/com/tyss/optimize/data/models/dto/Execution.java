package com.tyss.optimize.data.models.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.*;
import com.tyss.optimize.data.models.dto.results.DependantScriptResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Execution extends BaseEntity {
    @ApiModelProperty(notes = "The Execution Id")
    public String executionId;    @ApiModelProperty(notes = "The Project Name")
    public String projectName;
    @ApiModelProperty(notes = "The Project Id")
    public String projectId;
    @ApiModelProperty(notes = "true/false")
    public String screenshotRequired;
    @ApiModelProperty(notes = "true/false")
    public String videoRequired;
    @ApiModelProperty(notes = "The delay in seconds")
    public String delayBetweenSteps;
    @ApiModelProperty(notes = "The Client Id")
    public String clientId;
    @ApiModelProperty(notes = "Total Scripts")
    public Long totalScripts;
    @ApiModelProperty(notes = "Execution Start Time")
    public LocalDateTime executionStartTime;
    @ApiModelProperty(notes = "Execution Start Time of Script")
    public LocalDateTime scriptExecutionStartTime;
    @ApiModelProperty(notes = "The details of DependantScript")
    public List<ExecutionEntity> dependentScript;
    @ApiModelProperty(notes = "The details of Selected System")
    public List<SelectedSystem> selectedSystem;
    @ApiModelProperty(notes = "The details about Global Variables")
    public Map<String,String> globalVariables;
    @ApiModelProperty(notes = "The details about Project Environment Variables")
    public Map<String,String> projectEnvironmentVariables;
    @ApiModelProperty(notes = "The name of the files from where the data has to be read")
    public List<String> testData;
    @ApiModelProperty(notes = "The details of ExecutionEntity")
    public List<ExecutionEntity> executionEntity;
	@ApiModelProperty(notes = "The details of DependantScriptResponse")
    public List<DependantScriptResponse> dependantScriptResult;
	private StorageInfo storageDetails;
    private String installedDir;
    private String suiteName;
    private String suiteId;
    private String suiteAccess;
    @ApiModelProperty(notes = "true/false")
    public String failedStepsScreenshot;
    @ApiModelProperty(notes = "Wait Time Configuration")
    public WaitTime waitTime;
    @ApiModelProperty(notes = "Execution Termination Configuration")
    public ExecutionTermination executionTermination;
    private List<DriverInfo> driverInfoList;
    private Boolean driverSynced;
    private Boolean isDriversNotMatched = false;
    private String driverVersion;
    private String type;
    private String licenseId;
    private ClientSystemConfig clientSystemConfig;
    private Map<String, Map<String, Object>> capabilities;
    private Boolean isDriverSyncFirstTimeSuccessfully = false;
    private Boolean messageSent;
    private Map<String, Locator> elementDetails;
    private String userId;
}


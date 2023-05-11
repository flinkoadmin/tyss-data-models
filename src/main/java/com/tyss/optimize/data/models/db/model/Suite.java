package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.StorageInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "suites")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suite extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "SUITE";

	private String id;
	@NotBlank(message = "suite name is mandatory")
	@Pattern(message = "suite name contains special character", regexp = "^[-a-zA-Z0-9-()_]+(\\s+[-a-zA-Z0-9-()_]+)*$")
	private String name;
	private String description;
	private String projectId;
	@NotBlank(message = "sync scripts is mandatory")
	@ApiModelProperty(notes = "NO_SYNC / AUTO / MANUAL_SYNC")
	private String syncScripts;
	private String suiteType;
	private String noOfScripts;
	@ApiModelProperty(notes = "Public / Private")
	private String access;
	private String runningInstances;

	@NotNull(message = "Please select modules/scripts")
	private List<Object> selectedModulesAndScripts;
	private Map<String, String> selectedModulesStatus;

	private ExecutionDashboard executionDashboard;
	//Project Environment Variables
	private String usePeVariableFrom;
	private List<String> projectEnvironmentVariables;
	private String selectedPESetName;
	private List<VariableSet> clonedProjectEnvironmentVariables;
	//Global Variables
	private String useGlobalVariableFrom;
	private List<String> globalVariables;
	private String selectedGVSetName;
	private List<VariableSet> clonedGlobalVariables;
	// Test Data
	private String useTestDataFrom;
	private List<Object> sourceTestData;
	private String selectedTDSetName;
	private List<TestDataSet> clonedTestDataSets;
	private Machines machines;
	private WaitTime waitTime;
	private ResultSetting resultSetting;
	private ExecutionTermination executionTermination;
	@NotNull(message = "email data is mandatory")
	private EmailData emailData;
	private Schedule schedule;
	@ApiModelProperty(notes = "Storage details")
	private StorageInfo storageDetails;
	@ApiModelProperty(notes = "suite execution result")
	private ExecutionResult executionResult;
	@ApiModelProperty(notes = "true/false")
	private String autoRunSuite;
	private boolean edit=true;
	
}

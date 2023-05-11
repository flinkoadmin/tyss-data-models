package com.tyss.optimize.data.models.db.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import com.tyss.optimize.data.models.dto.StorageInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreExecution {

	@ApiModelProperty(notes = "Project name")
    String projectName;
	
	@ApiModelProperty(notes = "Project id")
	String projectId;

	@ApiModelProperty(notes = "Project id")
	String licenseId;
	
	@ApiModelProperty(notes = "Screenshot required - true/false")
	String screenshotRequired;
	
	@ApiModelProperty(notes = "Video required - true/false")
	String videoRequired;
	
	@ApiModelProperty(notes = "Delay Between steps")
	String delayBetweenSteps;
	
	@ApiModelProperty(notes = "Type of delay between steps")
	String stepDelayType;
	
	@ApiModelProperty(notes = "Client/Machine id")
	String clientId;
	
	@ApiModelProperty(notes = "PreExecution Name")
	String name;
	
	@ApiModelProperty(notes = "Pre execution object - tree data")
	List<Object> executionTree;
	
	@ApiModelProperty(notes = "Execution selected system")
	List<SelectedSystem> selectedSystem;
	
	@ApiModelProperty(notes = "Execution id")
	String executionId;
	
	@ApiModelProperty(notes = "Global Variables Object")
	public Map<String,String> globalVariables;
	
	@ApiModelProperty(notes = "Project Environment Variables")
	public Map<String,String> projectEnvironmentVariables;
	
	@ApiModelProperty(notes = "List of all scripts")
	List<Document> listOfScripts; // Used while creating execution request for dependentScript and script set
	
	@ApiModelProperty(notes = "List of all scripts")
	List<Document> listOfStepGroups; // Used while creating execution request for stepGroup set
	
	@ApiModelProperty(notes = "List of all variables")
	List<Document> listOfVariables; // Used while creating execution request for variables
	
	@ApiModelProperty(notes = "Total script count")
	int totalScriptCount;
	
	@ApiModelProperty(notes = "SUITE / INDIVIDUAL")
	String type;
	
	@ApiModelProperty(notes = "Storage details")
	StorageInfo storageDetails;
	
	@ApiModelProperty(notes = "Only script run")
	ScriptRunDetails scriptRunDetails;
	
	@ApiModelProperty(notes = "If an execution is scheduled")
	boolean scheduled;

	@ApiModelProperty(notes = "true/false")
	String failedStepsScreenshot="true";

	@ApiModelProperty(notes = "Type of scripts used in an executon")
	String executionType;

	List<String> variableUsed;

	String suiteExecutionType;

	@ApiModelProperty(notes = "Unique step numbers steps")
	int stepNumber;

	@ApiModelProperty(notes = "Selected script types")
	HashSet<String> scriptTypes;

	@Transient
	Suite suite;

	boolean updateExecutionList;

	boolean headless;

}

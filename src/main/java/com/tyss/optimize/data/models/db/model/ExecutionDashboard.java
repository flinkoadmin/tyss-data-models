package com.tyss.optimize.data.models.db.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.Execution;
import com.tyss.optimize.data.models.dto.ResponseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "execution_dashboard")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutionDashboard extends BaseEntity {
	
	@Transient
    public static final String SEQUENCE_NAME = "EXECUTION";
	
	@Id
    public String id;
	
	@ApiModelProperty(notes = "Execution dashboard name")
	String name;
	
	@ApiModelProperty(notes = "Execution project id")
	String projectId;
	
	@ApiModelProperty(notes = "SUITE / INDIVIDUAL/ SUITE_OF_SUITE")
	String type;
	
	@ApiModelProperty(notes = "Suite Of Suites id")
	String suiteOfSuiteId;
	
	@ApiModelProperty(notes = "Suite id")
	String suiteId;
	
	@ApiModelProperty(notes = "Execution result status - Pass/Fail")
	String resultStatus;
	
	@ApiModelProperty(notes = "execution status - Running/Pending/Completed/Cancelled/Aborted")
	String executionStatus;

	@ApiModelProperty(notes = "sub execution status for Suite Of Suite - Running/Pending/Completed/Cancelled/Aborted")
	String subExecutionStatus;
	
	@ApiModelProperty(notes = "Client execution request object")
	Execution execution;

	@ApiModelProperty(notes = "Client execution request object")
	List<Execution> executions;

	@ApiModelProperty(notes = "Suite execution dashboard")
	List<ExecutionDashboard> suiteExecutions;
	
	@ApiModelProperty(notes = "Execution request")
	PreExecution preExecution;
	
	@ApiModelProperty(notes = "Execution start time")
	String executionStartTime;
	
	@ApiModelProperty(notes = "Total number of script in an execution")
	int totalScriptCount;
	
	@ApiModelProperty(notes = "Tree data for the completed execution")
	ResponseDTO fancyTreeData;
	
	@ApiModelProperty(notes = "list of clients")
	List<String> clients;
	
	@ApiModelProperty(notes = "Suite details")
	Suite suite;
	
	@ApiModelProperty(notes = "Only script run")
	ScriptRunDetails scriptRunDetails;
	
	@ApiModelProperty(notes = "License id for common db")
	String licenseId;

	@ApiModelProperty(notes = "Execution id")
	String executionId;
	
	@ApiModelProperty(notes = "Execution id of the reference for rerun and run only failed")
	String parentReferenceId;

	@ApiModelProperty(notes = "Type of scripts used in an executon")
	String executionType;

	@ApiModelProperty(notes = "Auto run execution")
	String autoRunExecution;

	@ApiModelProperty(notes = "Suite details")
	SuiteOfSuites suiteOfSuites;

	@ApiModelProperty(notes = "Run type of execution")
	String executionRunType;

	@ApiModelProperty(notes = "Parallel/Distribution/Sequential")
	String suiteExecutionRunType;

}

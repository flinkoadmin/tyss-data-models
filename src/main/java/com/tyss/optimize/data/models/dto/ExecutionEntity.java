package com.tyss.optimize.data.models.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.*;
import com.tyss.optimize.data.models.dto.results.ExecutionStatistics;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ExecutionEntity extends BaseEntity {
	@ApiModelProperty(notes = "The Module/Script Id")
	public String id, key, oldKey;
	@ApiModelProperty(notes = "The Module/Script Name")
	public String name, title;
	@ApiModelProperty(notes = "The type can be Module/Script")
	public String type;
	@ApiModelProperty(notes = "The type of a Script")
	public String scriptType;
	@ApiModelProperty(notes = "The Order of execution")
	public String order;
	//public String systemId;
	//public String deviceSerialNo;
	public String ifCheckPointIsFailed;
	@ApiModelProperty(notes = "The list of DependantScript")
	public List<DependentScript> dependentScript;
	@ApiModelProperty(notes = "The list of Steps")
	public List<Step> steps;
	@ApiModelProperty(notes = "The list of Conditions")
	public List<Condition> conditions;
	@ApiModelProperty(notes = "The Entity which contains Module/Script or both")
	public List<ExecutionEntity> executionEntity, children;
	@ApiModelProperty(notes = "The details of LocalVariables ")
	public Map<String, String> localVariables;
	public Map<String, String> stepGroupVariables;
	@ApiModelProperty(notes = "The Path of a SubCollection")
	public String subCollectionJsonPath;
	@ApiModelProperty(notes = "The list of Cascaded-Conditions")
	public List<Condition> cascadedConditions;
	@ApiModelProperty(notes = "The details of DataProvider")
	public List<DataProvider> dataProvider;
	@ApiModelProperty(notes = "Include conditions in an iteration with DataProvider : true/false")
	public Boolean includeConditions;
	public Boolean considerEachIterationAsAScript;
	@ApiModelProperty(notes = "The details of IncludedConditions, start and end iteration")
	public List<Step> iterationSteps;
	List<ConditionInfo> conditionInfo;
	public Map<String,List<String>> scriptExecutionDetails;
	@ApiModelProperty(notes = "unique id for dependent script")
    public String stepId;
	@ApiModelProperty(notes = "last sync for test-data-set")
	public String lastSync;
	@ApiModelProperty(notes = "selected model & script of suite's folders")
	public boolean folder;
	public int fileCount;
	public ExecutionStatistics stepResultStats, scriptStats, moduleStats;
	public List<ExecutionStatistics>  platformStatistics;
	public SelectedSystem selectedSystem;
	public Object testCaseType;
	public String status;
	public String executedOn;
	public Map<String, Object> manualTestCase;
}

package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import com.tyss.optimize.data.models.dto.StorageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ExecutionEntityResponse extends BaseEntity {
    public String id,key;
    public String name;
    public String title;
    public String type;
    public String status;
    public String dependantStatus;
    public SelectedSystem selectedSystem;
    public String androidDeviceSerialNo;
    public String iosDeviceSerialNo;
   // public String platform;
    public String executedOn;
    public Long executionDuration;
    public String  executionDurationInHourMinSecFormat;
    public ExecutionStatistics preConditionStats;
    public ExecutionStatistics moduleLevelPreConditionStats;
    public ExecutionStatistics stepResultStats;
    public ExecutionStatistics postConditionStats;
    public ExecutionStatistics moduleLevelPostConditionStats;
    public ExecutionStatistics dependantScriptStats;
    public ExecutionStatistics executionEntityStats;
    public ExecutionStatistics scriptStats;
    public ExecutionStatistics moduleStats;
    public List<ExecutionStatistics>  platformStatistics;
    public Map<String,ExecutionStatistics> platformStatisticsMap;
    public List<DependantScriptResponse> dependantExecutionEntityResponses;
    public Condition preConditions;
    public List<StepResult> stepResults;
    public List<ExecutionEntityResponse> executionEntityResponses;
    public Condition postConditions;
    public Integer iteration;
    public ErrorInfo errorInfo;
    public String logicalStatus;
    private boolean folder=true;
    private List<?> children;
    private Boolean includeConditions;
    private String scriptType;
    private String stepId;
    public String executionId;
    private StorageInfo storageDetails;
    //public Integer noOfExecutionEntityResponses;
}

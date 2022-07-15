package com.tyss.optimize.data.models.dto.results;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Condition {
    public String id,key;
    public String name;
    public String title;
    public List<StepResult> stepResults;
    public String type;
    public String executedOn;
    public Long executionDuration;
    public String  executionDurationInHourMinSecFormat;
    public String status;
    public ExecutionStatistics stepResultStats;
    private boolean folder=false;
    private boolean isPreCondition;
    private boolean isPostCondition;
    private List<?> children;


}

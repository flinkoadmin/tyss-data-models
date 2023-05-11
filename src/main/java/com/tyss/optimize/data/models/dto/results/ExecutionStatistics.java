package com.tyss.optimize.data.models.dto.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionStatistics {
    public Integer total=0;
    public Integer totalPassed=0;
    public Integer totalFailed=0;
    public Integer totalWarning=0;
    public Integer totalSkipped=0;
    public Integer totalTerminated=0;
    public Integer totalAborted=0;
    public Integer totalNA=0;
    public Integer totalPartiallyExecuted=0;
    public Long executionDuration=0l;
    public String  executionDurationInHourMinSecFormat="";
    public String os;
    public String browser;
    //public String version;
    public String machine;
    public String executionEnv;
    public String deviceName;
    public String executedOn;
    public String platform;
    public String deviceOs;
    public String status;
}

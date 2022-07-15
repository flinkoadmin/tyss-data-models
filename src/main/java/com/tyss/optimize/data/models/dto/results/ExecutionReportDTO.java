package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionReportDTO {
    public String executionID;
    public String suiteName;
    public String status;

    public String duration;
    public String executedOn;
    public String executionType;
    public String totalNoOfMachines;
    public JSONObject moduleStats;
    public JSONObject scriptStats;
    public JSONObject preConditionStats;
    public JSONObject postConditionStats;
    public String os;
    public String browser;
    public String device;
    public String machine;
    public String executionEnvironment;
    public List<ExecutionReportData> executionReportData;




}

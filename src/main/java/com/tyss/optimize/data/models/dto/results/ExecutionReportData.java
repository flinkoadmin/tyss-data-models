package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionReportData {
    public String machine;
    public String executionEnvironment;
    public String os;
    public String browser;
    public String device;
    //used on parallel or distributed pdf report
    public String deviceOs;
    public String status;
    public String machineUser;
    public JSONObject moduleStats;
    public JSONObject scriptStats;
    public JSONObject preConditionStats;
    public JSONObject postConditionStats;
    public List<ExecutionReportTableData> executionReportTableDataList;

}

package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.cache.jcache.interceptor.JCacheOperationSource;

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
    public JSONObject moduleStats;
    public JSONObject scriptStats;
    public JSONObject preConditionStats;
    public JSONObject postConditionStats;
    public List<ExecutionReportTableData> executionReportTableDataList;

}

package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import com.tyss.optimize.data.models.db.model.Locator;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;

import com.tyss.optimize.data.models.dto.kafka.FireflinkMessageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Document(value = "ExecutionResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionResponse extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "EXECUTIONRESPONSE";

    @Id
    public String id;
    public String key;
    public String executionId;
    public String projectName;
    public String projectId;
    public String clientId;
    public String executedOn;
    public Long executionDuration;
    public String machine;
    public String  executionDurationInHourMinSecFormat;
    public ExecutionStatistics executionEntityStats;
    public ExecutionStatistics dependantExecutionEntityStats;
    public ExecutionStatistics scriptStats;
    public ExecutionStatistics moduleStats;
    public List<ExecutionStatistics>  platformStatistics;
    public Map<String,ExecutionStatistics> platformStatisticsMap;
    public List<ExecutionEntityResponse> dependantExecutionEntityResponses;
    //public List<Condition> preConditions;
    public List<ExecutionEntityResponse> executionEntityResponses;
    //public List<Condition> postConditions;
    public String status;
    public String executionStatus;
    public ErrorInfo errorInfo;
    public Map<String, String> executionStatusObject;
    private StorageInfo storageDetails;
    private Map<String, Locator> elementDetails;
    private String suiteName;
    private String suiteId;
    private String licenseId;
    private String userId;
    private String suiteAccess;
    private String updateType;
    private String manualUser;
    public Map<String, Object> manualUpdateObject;
    String payloadReference;
    String objectId;
    private FireflinkMessageDto fireflinkMessageDto;
    @ApiModelProperty(notes = "Tree data for the completed execution")
    ResponseDTO fancyTreeData;
    @ApiModelProperty(notes = "If tree data exists")
    boolean treeDataExists;
}

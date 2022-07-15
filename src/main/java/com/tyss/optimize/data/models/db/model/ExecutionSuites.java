package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutionSuites {

    String name;
    String createdOn;
    String createdBy;
    String lastExecutedOn;
    String lastExecutedBy;
    String executionId;
    String clientId;
    String duration;
    String executionType;
    String hostName;
    List<ExecutionSuites> executionResponses;
    String status;
    SuiteData moduleInfo;
    SuiteData scriptInfo;
}

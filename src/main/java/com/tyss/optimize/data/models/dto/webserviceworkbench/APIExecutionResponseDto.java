package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.Variable;
import com.tyss.optimize.data.models.dto.webserviceworkbench.assertion.AssertionResultDto;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIExecutionResponseDto {
    private String id;
    private boolean isSuccess;
    private int statusCode;
    private String statusCodeValue;
    private List<NameValueDto> headers;
    //Map<String,String> headers;
    private Map<String,Object> metaData = new HashMap<>();
    private String responseBody;
    private StepResultDto preStepResult;
    private StepResultDto postStepResult;
    List<AssertionResultDto> responseAssertionResults;
    private AssertionResult assertionResult;
    private List<AssignVariableValueDto> updatedVariables;
}

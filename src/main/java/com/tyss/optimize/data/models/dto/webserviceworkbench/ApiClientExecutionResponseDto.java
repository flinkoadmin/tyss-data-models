package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiClientExecutionResponseDto {
    private APIExecutionResponseDto executionResponse;
    private AssertionResult assertionResult;
}

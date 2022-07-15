package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.tyss.optimize.data.models.dto.webserviceworkbench.assertion.AssertionResultWrapperDto;
import lombok.Data;

import java.util.Map;

@Data
public class StepResultDto {
    private Map<String,Object> outputAttributes;
    private AssertionResultWrapperDto assertionResult;
}

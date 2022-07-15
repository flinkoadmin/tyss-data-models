package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.tyss.optimize.data.models.dto.webserviceworkbench.assertion.AssertionRequestDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class StepDto {
    @NotNull
    private ProgramDto program;
    private Map<String, AssignVariableValueDto> variablesToUpdate; //this can be static value or value from output attributes
    private List<AssertionRequestDto> nlpAssertions; // this should be on output attributes
}

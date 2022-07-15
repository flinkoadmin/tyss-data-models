package com.tyss.optimize.data.models.dto.webserviceworkbench.assertion;

import lombok.Data;

import java.util.List;

@Data
public class AssertionResultWrapperDto {
    private String statusCode;
    private String statusMessage;
    private List<AssertionResultDto> assertionResults;
}

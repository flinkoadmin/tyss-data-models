package com.tyss.optimize.data.models.dto.webserviceworkbench.assertion;

import lombok.Data;

import java.util.Set;

@Data
public class AssertionResultDto {
    private String message; //either fail message or pass message
    private String resultMessage; //either fail message or pass message
    private String state; //SUCCESS, FAILED, ERROR
    private Object expected; //RHS
    private Object actual; //assertion result
    private Set<String> validationErrors;
    private AssertionRequestDto assertionRequestDto;
}

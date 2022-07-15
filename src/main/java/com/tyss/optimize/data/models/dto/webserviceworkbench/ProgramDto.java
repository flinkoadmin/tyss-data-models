package com.tyss.optimize.data.models.dto.webserviceworkbench;

import lombok.Data;

import java.util.Map;

@Data
public class ProgramDto {
    private Map<String,String> inputAttributes;
    private CodeSnippet codeSnippet;
}

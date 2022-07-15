package com.tyss.optimize.data.models.dto.webserviceworkbench;

import lombok.Data;

import java.util.List;

@Data
public class CodeSnippet {
    private String code;
    private List<String> customSnippetDefinitions;
}

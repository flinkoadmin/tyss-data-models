package com.tyss.optimize.data.models.dto.webserviceworkbench;


import com.tyss.optimize.data.models.dto.webserviceworkbench.assertion.AssertionRequestDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiResponseDto {

    private String id;
    private String suiteId;
    private String url;
    private String name;
    private String description;
    private String method;
    private Object requestBody;
    private String bodyType;
    private List<NameValueDto> queryParams;
    private List<NameValueDto>  headers;
    private String authType;
    private Object auth;
    private Map<String, List<AppFileMinimalDto>> fileParamMap;
    private List<AssertionRequestDto> basicAssertions;
    private List<StepDto> nlpAssertions;
}

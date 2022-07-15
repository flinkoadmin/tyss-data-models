package com.tyss.optimize.data.models.dto.webserviceworkbench;
import com.tyss.optimize.data.models.dto.webserviceworkbench.assertion.AssertionRequestDto;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiRequestDto {
        private String id;
        private String url;
        private String name;
        private String suiteId;
        private String description;
        private String method = "GET";
        private Object requestBody;
        private List<NameValueDto> queryParams;
        private List<NameValueDto> headers;
        private String authType;
        private Object auth;
        private List<AssertionRequestDto> basicAssertions;
        private StepDto preStep;
        private StepDto postStep;
        private Map<String, List<AppFileMinimalDto>> fileParamMap;
        private Map<String, AssignVariableValueDto> updateVariables; //set these variables based on response of the actual API invocation
        private List<StepDto> nlpAssertions;//TODO revisit this later
        private Map<String,Object> clientVariableMap;
}

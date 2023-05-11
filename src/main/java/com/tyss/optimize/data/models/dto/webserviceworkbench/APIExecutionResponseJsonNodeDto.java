package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIExecutionResponseJsonNodeDto {

    private int statusCode;
    private String statusCodeValue;
    private List<NameValueDto> headers;
    //Map<String,String> headers;
    private Map<String,Object> metaData = new HashMap<>();
    private Object responseBody;
    public APIExecutionResponseJsonNodeDto(APIExecutionResponseDto apiExecutionResponseDto){
        ObjectMapper mapper = new ObjectMapper();
        try {
            boolean isJsonContent = (Objects.nonNull(apiExecutionResponseDto.getHeaders())) ? apiExecutionResponseDto.getHeaders().stream().anyMatch(header->header.getName().equalsIgnoreCase("Content-Type") && header.getValue().contains("application/json")) : false;
            if (isJsonContent) {
                this.responseBody = mapper.readTree(apiExecutionResponseDto.getResponseBody());
            } else {
                this.responseBody = apiExecutionResponseDto.getResponseBody();
            }
        } catch (JsonProcessingException e) {
            this.responseBody= apiExecutionResponseDto.getResponseBody();
            e.printStackTrace();
        }
        this.headers= apiExecutionResponseDto.getHeaders();
        this.metaData=apiExecutionResponseDto.getMetaData();
        this.statusCode=apiExecutionResponseDto.getStatusCode();
        this.statusCodeValue=apiExecutionResponseDto.getStatusCodeValue();
    }
}


package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIExecutionResponseJsonNodeDto {

    private int statusCode;
    private List<NameValueDto> headers;
    //Map<String,String> headers;
    private Map<String,Object> metaData = new HashMap<>();
    private JsonNode responseBody;
    public APIExecutionResponseJsonNodeDto(APIExecutionResponseDto apiExecutionResponseDto){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(apiExecutionResponseDto.getResponseBody());
            this.responseBody= jsonNode;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.headers= apiExecutionResponseDto.getHeaders();
        this.metaData=apiExecutionResponseDto.getMetaData();
        this.statusCode=apiExecutionResponseDto.getStatusCode();
    }
}


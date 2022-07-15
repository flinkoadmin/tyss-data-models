package com.tyss.optimize.data.models.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private int responseCode;
    private Object responseObject;
    private int errorCode;
    private String message;
    private String status;
    private Map<String,Object> responseMap;
}

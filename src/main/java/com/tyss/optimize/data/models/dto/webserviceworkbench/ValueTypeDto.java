package com.tyss.optimize.data.models.dto.webserviceworkbench;

import lombok.Data;

@Data
public class ValueTypeDto {
    private String path;
    private String outputAttributeName;
    private Object value;
}
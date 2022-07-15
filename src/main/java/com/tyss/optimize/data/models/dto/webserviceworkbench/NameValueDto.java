package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameValueDto {
    private Boolean isEnabled = false;
    private String name;
    private String value;

    public NameValueDto(String key, String value ,Boolean isEnabled) {
        this.name = key;
        this.value = value;
        this.isEnabled = isEnabled;
    }
}

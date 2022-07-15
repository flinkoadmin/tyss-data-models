package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Distribution {
    private List<String> selectedModules;
    private String clientSystemId;
    private String estimatedDuration;
}

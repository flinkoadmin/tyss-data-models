package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitIssue extends BaseEntity {

    private String instanceName;
    private String title;
    private String description;
    private String type;
    private List<String> labels;
    private String due_date;
}

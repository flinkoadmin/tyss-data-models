package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.results.VariableDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "TagDetails")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TagDetails extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "TAGDETAIL";

    @Id
    private String id;
    private String name;
    private String description;
    private String type;
    private String scope;
    private String suiteId;
    private String projectId;
    private String elementId;
    private String scriptId;
    private String exceptionName;
    private String nlpName;
    private Boolean updateEveryWhere;
    private Boolean updateAllSteps;
    @Transient
    private String action;
    private String stepId;
    @Transient
    private String key;
}

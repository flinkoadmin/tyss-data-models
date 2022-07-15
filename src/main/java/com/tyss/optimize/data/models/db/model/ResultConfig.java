package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Document(value = "result_configs")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResultConfig extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "RESULT_CONFIG";

    @Id
    public String id;
    private Integer noOfTestDevResults;
    private Integer noOfScriptsResults;
    private Integer noOfModulesResults;
    private Integer noOfSuitesResults;
    private Integer noOfSuitesOfSuiteResults;
    private String projectId;
}

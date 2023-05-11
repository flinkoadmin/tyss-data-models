package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.results.ExecutionEntityResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Script extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "SCRIPT";

    @JsonAlias("_id")
    String id;
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")

    String name;

    @ApiModelProperty(notes = "WEB/Database/Webservice")
    @NotNull(message = "Script type is mandatory")
    @NotBlank(message = "script type must not be blank")
    String scriptType;
    @Size(max = 200, message = "Description must be 200 characters")
    String desc;
    String type;
    @NotNull(message = "testCaseType is mandatory")
    @NotBlank(message = "testCaseType must not be blank")
    String testCaseType;
    String templateId;
    double executionOrder;
    List<Step> steps;
    int stepCount;
    int hierarchy;
    List<String> selectedSystems;
    String delayBetweenSteps;
    List<DependentScript> dependentScript;
    List<Conditions> conditions;
    Map<String, String> localVariables;
    List<DataProvider> dataProvider;
    ExecutionEntityResponse scriptResult;
    int preConditionCount;
    int postConditionCount;
    int dependentScriptCount;
    Map<String, Object> manualTestCase;

}

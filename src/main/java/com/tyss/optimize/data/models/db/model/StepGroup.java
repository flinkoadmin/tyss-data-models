package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.ReferenceDetails;
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
public class StepGroup extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "STEP_GROUP";

    String id;

    @Size(min = 2, message = "Name must have 2 or more characters")
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    String name;
    @NotNull(message = "type is mandatory")
    String type;
    @Size(max = 200,message ="Description must be 200 characters" )
    String desc;
    double executionOrder;
    String createdTime;
    List<Step> steps;
    List<String> selectedSystems;
    Map<String,String> localVariables;
    Map<String, Object> stepGroupVariables;
    ReferenceDetails stepGroupReferenceInfo;
    List<Variable> variables;
    int stepCount;
    private boolean folder;
    @Size(min = 2, message = "passMessage must have 2 or more characters")
    String passMessage;
    @Size(min = 2, message = "failMessage must have 2 or more characters")
    String failMessage;
    List<Parameter> parameters;
    @NotNull(message = "returnType is mandatory")
    String returnType = "void";
    String toolTip;
    boolean defaultStepGroup=false;
    String displayName;
    int hierarchy;
}

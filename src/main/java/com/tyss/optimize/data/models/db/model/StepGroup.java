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

    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 100, message
            = "Name must be between 3 and 100 characters")
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
    int stepCount;
    private boolean folder;
    @Size(max =110,message = "passMessage must be 110 characters")
    String passMessage;
    @Size(max =110,message = "failMessage must be 110 characters")
    String failMessage;
    List<Parameter> parameters;
    @NotNull(message = "returnType is mandatory")
    String returnType = "void";
    String toolTip;
    boolean defaultStepGroup=false;
    String displayName;
}

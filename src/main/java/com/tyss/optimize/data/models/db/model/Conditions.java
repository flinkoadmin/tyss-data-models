package com.tyss.optimize.data.models.db.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.ReferenceDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "conditions")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Conditions extends  BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "PRE_POST_CONDITION";
    @Id
    @JsonAlias("_id")
    @ApiModelProperty(notes = "The Condition Id")
    public String id;
    public String key;

    @ApiModelProperty(notes = "The Module Id")
    String moduleId ;

    @ApiModelProperty(notes = "The Cascaded Condition Id")
    String cascadedReference;

    @ApiModelProperty(notes = "The Condition name")
    String name, title;

    @NotNull(message = "stepGroupId is mandatory")
    String stepGroupId;

    @NotNull(message = "stepGroupName is mandatory")
    String stepGroupName;

    @ApiModelProperty(notes = "StepGroup LibraryId")
    String libraryId;

    private List<StepInput> stepInputs;

    @NotNull(message = "type is mandatory")
    @ApiModelProperty(notes = "The Condition Type : PreCondition/PostCondition")
    String type;

    @ApiModelProperty(notes = "The details of Step")
    List<Step> steps, children;

    @NotNull(message = "ifCheckPointIsFailed is mandatory")
    String ifCheckPointIsFailed;

    @NotNull(message = "platform is mandatory")
    String platform;

    @ApiModelProperty(notes = "true/false")
    String cascaded;

    @ApiModelProperty(notes = "The condition from where the Cascade starts")
    public String cascadedFrom;

    @ApiModelProperty(notes = "The Module Name from where it is cascaded")
    public String moduleName;

    @NotNull(message = "status is mandatory")
    String status;
    @NotNull(message = "executionOrder is mandatory")
    double executionOrder;

    @ApiModelProperty(notes = "unique id for each condition")
    public String stepId;

    @ApiModelProperty(notes = "The details of the variable in which Step return value is stored")
    public ReferenceDetails stepReferenceInfo;

    @ApiModelProperty(notes = "Hierarchy for each condition")
    int hierarchy;

    public String returnType;


}

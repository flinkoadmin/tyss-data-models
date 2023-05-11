package com.tyss.optimize.data.models.db.model;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.ReferenceDetails;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Step extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "STEP";

    @ApiModelProperty(notes = "The Step Id")
    @Id
    private String id;

    @ApiModelProperty(notes = "The Step Name")
    @Size(min = 2, message = "Name must have 2 or more characters")
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    private String name;
    private String tempName;
    @NotBlank(message = "stepType is mandatory")
    private String type;

    @ApiModelProperty(notes = "The List of Steps")
    private List<Step> steps;

    @ApiModelProperty(notes = "The List of Steps from preExecution")
    private List<Step> children;
    
    @ApiModelProperty(notes = "The Nlp Name")
    @NotBlank(message = "nlpName is mandatory")
    private String nlpName;

    @ApiModelProperty(notes = "Execution Order")
    double executionOrder;

    @NotBlank(message = "nlpId is mandatory")
    private String nlpId;

    @ApiModelProperty(notes = "The Pass Message")
    @Size(min = 2, message = "passMessage must have 2 or more characters")
    private String passMessage;

    @ApiModelProperty(notes = "The Fail Message")
    @Size(min = 2, message = "failMessage must have 2 or more characters")
    private String failMessage;

    @ApiModelProperty(notes = "The Details of Element")
    public List<Element> elementDetails;

    @ApiModelProperty(notes = "The details of the variable in which Step return value is stored")
    public ReferenceDetails stepReferenceInfo;

    @ApiModelProperty(notes = "The details of the variable in which StepGroup return value is stored")
    public ReferenceDetails stepGroupReferenceInfo;

    @ApiModelProperty(notes = "The details of StepInputs/Parameter of an NLP")
    private List<StepInput> stepInputs;

    @ApiModelProperty(notes = "The List of Grouped Steps")
    public List<Step> groupedSteps;

    @ApiModelProperty(notes = "The Action to be performed on failure")
    public String failureAction;

    @ApiModelProperty(notes = "The details of StepInput")
    public List<StepInput> conditionReference;

    @ApiModelProperty(notes = "Iteration/step/ForCondition")
    public String groupType;
    @ApiModelProperty(notes = "true/false")
    public Boolean skip = false;

    @ApiModelProperty(notes = "iteration number")
    public Integer iteration;

    @NotNull(message = "ifCheckPointIsFailed is mandatory")
    String ifCheckPointIsFailed;

    @NotNull(message = "return Type is mandatory")
    String returnType;

    @ApiModelProperty(notes = "true/false")
    String cascaded;

    @ApiModelProperty(notes = "The script from where the Cascade starts")
    public String cascadedFrom;
    
    @ApiModelProperty(notes = "Status of cascaded condition Enabled/Disabled")
    public String status;

    @ApiModelProperty(notes = "StepGroup Variables")
    Map<String, Object> stepGroupVariables;

    @ApiModelProperty(notes = "The LibraryId for StepGroup")
    public String libraryId;

    @ApiModelProperty(notes = "The details of StepGroupSteps in inputParameter")
    public String stepGroupId;

    @ApiModelProperty(notes = "The packageId for programElement")
    public String packageId;

    @ApiModelProperty(notes = "The details of programElement in inputParameter")
    public String programElementId;

    @ApiModelProperty(notes = "nlpType for nlp")
    public String nlpType;

    @ApiModelProperty(notes = "platform for nlp")
    public String platform;

    @ApiModelProperty(notes = "packageName for nlp")
    public String packageName;

    @ApiModelProperty(notes = "searchName for nlp")
    public String searchName;

    @ApiModelProperty(notes = "displayName for nlp")
    public String displayName;

    @ApiModelProperty(notes = "description for nlp")
    public String description;

    @ApiModelProperty(notes = "actualFailedResult for nlp")
    public String actualFailedResult;

    @ApiModelProperty(notes = "defaultDisplayName for nlp")
    public String defaultDisplayName;
    
    @ApiModelProperty(notes = "unique id for each step")
    public String stepId;

    @ApiModelProperty(notes = "toolTip for each step or nlp")
    public String toolTip;

    @ApiModelProperty(notes = "defaultToolTip for each step")
    public String defaultToolTip;

    @ApiModelProperty(notes = "Hierarchy for cascaded condition")
    int hierarchy;

    @ApiModelProperty(notes = "Module name from where the step group is cascaded")
    public String cascadedModuleName;

    public String specialNlpId;

    public Boolean isSpecialNlp;

    public String parentSpecialNlpId;

    public Double marginLeft;

    public List dataProviderVariables;

    public Boolean isIterationStep;

    String conditionId;

    String conditionSearchKey;
}

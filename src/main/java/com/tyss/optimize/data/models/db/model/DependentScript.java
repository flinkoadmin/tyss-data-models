package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependentScript extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "DEPENDENT_SCRIPT";

    String id;
    @NotBlank(message = "moduleId is mandatory")
    String moduleId;
    @NotBlank(message = "scriptId is mandatory")
    String scriptId;
    String name;
    @NotBlank(message = "ifCheckPointIsFailed is mandatory")
    String ifCheckPointIsFailed;
    double executionOrder;
    @ApiModelProperty(notes = "unique id for each dependentScript")
    public String stepId;
}

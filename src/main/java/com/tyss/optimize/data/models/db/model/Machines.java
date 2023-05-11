package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Machines {
    private String multiple;
    @ApiModelProperty(notes = "SEND_SUITE_TO_ALL / DISTRIBUTE")
    private String executionType;
    @ApiModelProperty(notes = "AUTOMATIC / MANUAL")
    private String distributionMechanism;
    private List<Distribution> distribution;
    @NotNull(message = "Selected machines is mandatory")
    List<SelectedSystem> selectedMachines;
    private String userExecutionType;
    private String totalEstimatedDuration;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutomaticDistribution extends BaseEntity{

    @NotNull(message = "modules is mandatory")
    List<Object> selectedModulesAndScripts;

    @NotNull(message = "machines is mandatory")
    List<SelectedSystem> selectedMachines;

    @NotBlank(message = "distributionBy is mandatory")
    @ApiModelProperty(notes = "NO_OF_SCRIPTS / DURATION / NO_OF_STEPS")
    String distributionBy;
}





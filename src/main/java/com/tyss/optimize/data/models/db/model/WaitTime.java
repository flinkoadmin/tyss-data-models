package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaitTime {
    @NotBlank(message = "implicit wait is mandatory")
    private String implicitWait;
    private String implicitWaitUnit;
    @NotBlank(message = "explicitly wait is mandatory")
    private String explicitlyWait;
    private String explicitlyWaitUnit;
    @NotBlank(message = "delay between steps is mandatory")
    private String delayBetweenSteps;
    private String delayBetweenStepsUnit;
}

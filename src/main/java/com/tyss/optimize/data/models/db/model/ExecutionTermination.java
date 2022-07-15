package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutionTermination {
    @NotBlank(message = "'terminate script if takes more time' is mandatory")
    private String terminateScriptIfTakesMoreTime;
    // Hrs. / Mns. / Secs.
    private String terminateScriptUnit;
    // true / false
    private String terminateScriptSendEmail;
    @NotBlank(message = "'terminate suite if takes more time' is mandatory")
    private String terminateSuiteIfTakesMoreTime;
    // Hrs. / Mns. / Secs.
    private String terminateSuitetUnit;
    // true / false
    private String terminateSuiteSendEmail;
}

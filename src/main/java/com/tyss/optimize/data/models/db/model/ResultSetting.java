package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultSetting {
    // Only for failed Steps / For all Steps(Pass or Fail) / Don't Capture
    private String captureScreenshots;
    private String captureVideoForFailedTestScript;
    // On suite execution start / On suite execution completion/termination
    private String displayLogsInScriptResult;
}

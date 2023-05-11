package com.tyss.optimize.data.models.db.model.fireSpiders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scripts {

    private String scriptId;
    private Integer manualTestCaseStepCount;
    private Integer automationScriptsStepCount;
    private Integer automationPassCount;
    private Integer automationFailCount;
    private String createBy;
    private Date createDate;
    private Date updatedDate;
    private String scriptType;
    private String platformType;
    private String status;
}

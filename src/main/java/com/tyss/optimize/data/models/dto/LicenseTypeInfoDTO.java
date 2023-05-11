package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.Plans;
import com.tyss.optimize.data.models.db.model.Promotions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseTypeInfoDTO {
    private String type;
    private List<Plans> plans;
    private long numberOfParallelRuns;
    private long storage;
    private long keepDataAfterExpiry;
    private int sendReminderForUpgrading;
    private List<Promotions> promotions;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutionSchedules {

    String name;
    String recurrences;
    String scheduledTime;
    String scheduledOn;
    String expiresOn;
    String expireTime;
    String machineName;
    String executionEnvironment;
    String executionType;
    List<String> platform;
    String createdOn;
    String scheduledBy;
    String  suiteId;
    String suiteName;
    List<ExecutionSchedules> executionSchedules;
    List<SelectedSystem> selectedSystems;
}

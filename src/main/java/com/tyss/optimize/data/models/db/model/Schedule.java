package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Schedule {
    @NotBlank(message = "suite name is mandatory")
    private String suiteName;
    @NotBlank(message = "run in is mandatory")
    private String runIn;
    @NotBlank(message = "machine name is mandatory")
    private String machineName;
    // Once, Daily, Weekly, Monthly
    @NotBlank(message = "repeat is mandatory")
    private String repeat;
    @NotBlank(message = "schedule date is mandatory")
    private String scheduleDate;
    @NotBlank(message = "schedule time is mandatory")
    private String scheduleTime;
    @NotBlank(message = "expire date is mandatory")
    private String expireDate;
    @NotBlank(message = "expire time is mandatory")
    private String expireTime;
    @NotBlank(message = "update schedule date is mandatory")
    private String updatedScheduleDate;
    @NotBlank(message = "update schedule time is mandatory")
    private String updatedScheduleTime;
    @NotBlank(message = "update expire date is mandatory")
    private String updatedExpireDate;
    @NotBlank(message = "update expire time is mandatory")
    private String updatedExpireTime;
    private String estimatedDuration;
}

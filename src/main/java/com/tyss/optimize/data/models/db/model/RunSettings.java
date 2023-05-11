package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "run_settings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RunSettings extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "RUN_SETTINGS";

    @Id
    private String id;
    private String suiteId;
    private String action;
    private String suiteType;
    private String projectId;
    private Machines machines;
    private WaitTime waitTime;
    private ResultSetting resultSetting;
    private ExecutionTermination executionTermination;
}

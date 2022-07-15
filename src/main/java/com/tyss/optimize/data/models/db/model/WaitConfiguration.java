package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "wait_configs")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class WaitConfiguration extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "WAIT_CONFIG";

    @Id
    private String id;
    private Double explicitWait;
    private String explicitWaitUnit;
    private Double implicitWait;
    private String implicitWaitUnit;
    private Double delayBetweenSteps;
    private String delayBetweenStepsUnit;
    private String projectId;
}

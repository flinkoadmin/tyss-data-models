package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "license_type")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseTypeInfo {

    @Transient
    public static final String SEQUENCE_NAME = "LICENSE_TYPE";

    @Id
    private String id;
    private String type;
    private String paymentType;
    private List<Plans> plans;
    private long numberOfParallelRuns;
    private long storage;
    private long keepDataAfterExpiry;
    private int sendReminderForUpgrading;
    private List<Promotions> promotions;
}

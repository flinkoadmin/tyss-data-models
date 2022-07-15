package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "license_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseTypeInfo {
    private String id;
    private String type;
    private List<Plans> plans;
    private long numberOfParallelRuns;
    private long storage;
    private List<Promotions> promotions;
}

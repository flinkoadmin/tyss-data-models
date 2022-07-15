package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "default_configurations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultConfigurations extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "DFC";

    private String id;
    private String defaultProjectMemory;
    private String defaultImplicitWait;
}

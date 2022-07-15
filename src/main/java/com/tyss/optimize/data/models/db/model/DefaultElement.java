package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "default_elements")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultElement extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "DEFAULT_PROJECT_ELEMENT";

    String id;
    String name;
    String projectType;
    String platform;
    String appType;
    List<String> elementTypes;
    List<String> locatorTypes;
    List<ElementType> allElementTypes;
    List<LocatorType> allLocatorTypes;
    String projectId;
}

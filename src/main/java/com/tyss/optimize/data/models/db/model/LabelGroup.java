package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Document(value = "label_groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabelGroup extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "LABEL_GROUP";

    @Id
    public String id;

    @NotEmpty(message = "name is mandatory")
    private String name;

    @NotEmpty(message = "type is mandatory")
    private String type;

    private long cloneCount;

    private List<Label> labels;


}


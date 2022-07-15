package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(value = "email_template")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailTemplate extends BaseEntity{

    @Id
    String id;

    @Transient
    public static final String SEQUENCE_NAME = "EMAIL_TEMPLATE";

    @Size(min = 2, max = 50, message
            = "type must be between 2 and 50 characters")
    @NotNull(message = "type is mandatory")
    String name;

    String type;

    String desc;

    String template;

    String subject;

    String isActive;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Limit extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "LIMIT";

    @Id
    public String id;

    @NotEmpty(message = "name is mandatory")
    private String name;

    private String limit;

    private String type;

    private List<Limit> limits;
}


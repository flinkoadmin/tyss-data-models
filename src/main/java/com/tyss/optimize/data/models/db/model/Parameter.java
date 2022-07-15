package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "parameter")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Parameter extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "PARAMETER";

    @Id
    private String id;
    private String name;
    private String type;
    private List<String> usedInStep;
}

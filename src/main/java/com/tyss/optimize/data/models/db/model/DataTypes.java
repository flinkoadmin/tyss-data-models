package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "data_types")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataTypes extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "DATATYPE";

    @Id
    public String id;
    private String desc;
    private Map<String,String> dataTypes;
}

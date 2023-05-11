package com.tyss.optimize.data.models.db.model.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "config_props")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigProp {

    @Id
    public String id;
    @NotBlank(message = "Key is mandatory")
    private String key;
    private String value;
}

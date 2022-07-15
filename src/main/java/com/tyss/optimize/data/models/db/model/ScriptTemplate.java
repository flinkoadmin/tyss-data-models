package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Document(value = "script_template")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptTemplate extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "SCRIPT_TEMPLATE";

    @Id
    String id;
    @NotNull(message = "name is mandatory")
    @NotBlank
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-_]+$")
    String name;
    String templateType;
    String projectId;
    List<Map<String, Object>> testCaseDetails;
    Map<String, Object> testSteps;

}

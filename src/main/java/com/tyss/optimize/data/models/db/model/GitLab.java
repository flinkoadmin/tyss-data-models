package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "gitlab")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLab extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "GITLAB";

    @Id
    public String id;

    @Size(max = 100,min=2,message = "Name must be 2 and 100 character")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Project id is mandatory")
    private String projectId;

    @NotBlank(message = "Suite id is mandatory")
    private String suiteId;

    @NotBlank(message = "Project name is mandatory")
    private String projectName;

    @NotBlank(message = "Suite name is mandatory")
    private String suiteName;
}


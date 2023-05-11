package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Document(value = "git_instance")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitInstance  extends BaseEntity{

    public static final String SEQUENCE_NAME = "GIT_INSTANCE";

    @Id
    private String id;
    @Size(min = 2, max = 100, message = "name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-_]+$")
    private String name;
    private String url;
    private String personalToken;
}

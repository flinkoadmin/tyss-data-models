package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "project_types")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectType extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "PROJECT_TYPE";

    @Id
    public String id;

    @NotEmpty(message = "name is mandatory")
    private String name;

    private String type;

    private Long cloneCount;

    private List<Feature> features;

    private Long webCount;
    private Long mobileCount;
    private Long webAndMobileCount;
    private Long webservicesCount;
    private Long salesforceCount;
}


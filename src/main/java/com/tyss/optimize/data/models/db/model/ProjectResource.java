package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResource {

    Long suites;
    Long modules;
    Long scripts;
    Long projectElements;
    Long programElements;
    Long stepGroups;
    Long testDataFiles;
    Long projectEnvironmentVariables;
    Long globalVariables;

}

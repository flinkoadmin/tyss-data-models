package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Browser extends BaseEntity {

    @NotNull(message = "name is mandatory")
    private String name;

    @NotNull(message = "version is mandatory")
    private String version;

    private String type;

    List<String> versions;

    Long count;
}

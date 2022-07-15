package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {
    @NotNull(message = "Primary Phone is mandatory")
    private String primaryPhone;

    private String secondaryPhone;

}

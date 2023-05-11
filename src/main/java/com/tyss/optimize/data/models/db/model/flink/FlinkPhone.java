package com.tyss.optimize.data.models.db.model.flink;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlinkPhone {
    private String primaryPhone;
    private String secondaryPhone;
}

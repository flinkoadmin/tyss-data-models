package com.tyss.optimize.data.models.db.model.flink;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlinkLicense {
    private String _id;
    private String privilege;
}

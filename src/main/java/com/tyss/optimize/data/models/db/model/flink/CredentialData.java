package com.tyss.optimize.data.models.db.model.flink;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialData {
    private String value;
    private String salt ;
    private String algorithm;
    private Integer iterations;
    private String type;

}

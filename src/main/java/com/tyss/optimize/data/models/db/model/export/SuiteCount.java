package com.tyss.optimize.data.models.db.model.export;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuiteCount {
    long maxAllowedSuiteCount;
    long totalSuiteCount;
}

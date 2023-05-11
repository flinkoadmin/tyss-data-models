package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Plans {
    private String plan;
    private long priority;
    private double monthlyCharge;
    private double yearlyCharge;
    private double additionalParallelRunsCost;
    private double additionalStorageCost;
}

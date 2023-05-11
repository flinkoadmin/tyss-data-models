package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    private double numberOfParallelRuns;
    private double storage;
    private double totalCostForNumberOfParallelRuns;
    private double totalCostForStorage;
    private double balance;
    private double paidForDays;
    private double upgradeCharges;
    private double netTotal;
    private double tax;
    private double grandTotal;

}

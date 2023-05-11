package com.tyss.optimize.data.models.db.model.export;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.Conditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportVariables {
    Map<String,String> mappingTable = new HashMap<>();
    Map<String,String> storageInfo = new HashMap<>();
    Map<String,String> currentStorageInfo = new HashMap<>();
    Map<String,String> defaultLibraryIds = new HashMap<>();
    Map<String, List<Conditions>> preExistConditions = new HashMap<>();
    Set<String> defaultStepGroupNameSet = new HashSet<>();
    int preConditionCount = 0;
    int postConditionCount = 0;
    double defaultExecutionOrder = 1.0d;
}

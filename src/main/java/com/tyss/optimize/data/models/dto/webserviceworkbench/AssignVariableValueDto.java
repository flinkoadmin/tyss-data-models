package com.tyss.optimize.data.models.dto.webserviceworkbench;

import com.tyss.optimize.data.models.db.model.Variable;
import lombok.Data;

@Data
public class AssignVariableValueDto {
    private Variable variable;
    private ValueTypeDto valueType;
    private String group="Unassigned";
    private Boolean isEnabled = false;
}

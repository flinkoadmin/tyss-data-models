package com.tyss.optimize.data.models.db.model.export;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleCount {
    long maxAllowedScriptCount;
    long totalScriptCount;
    Map<String,Long> scriptOfModuleMap;
}

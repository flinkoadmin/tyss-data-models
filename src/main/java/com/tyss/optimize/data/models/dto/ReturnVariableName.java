package com.tyss.optimize.data.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnVariableName {
	
	 public String variableName;
	 public String variableType;
}

package com.tyss.optimize.data.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDetails {
	@ApiModelProperty(notes = "The step number of the step in the stepGroup")
	public int stepNumber;
	@ApiModelProperty(notes = "The name of the variable in which Step return value is stored")
	public String name;
	@ApiModelProperty(notes = "Local/Global")
	public String type;
	public String value;
	public String referenceId;
}

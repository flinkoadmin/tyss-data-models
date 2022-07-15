package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepInput extends BaseEntity{

	@ApiModelProperty(notes = "The StepInput/Parameter Id")
	public String id;
	@ApiModelProperty(notes = "The StepInput/Parameter Value")
	public String value;
	@ApiModelProperty(notes = "The StepInput/Parameter Name")
	public String name;
	@ApiModelProperty(notes = "The StepInput/Parameter Type")
	public String type;
	@ApiModelProperty(notes = "GLOBAL/LOCAL/DATAPROVIDER")
	public String reference;
	@ApiModelProperty(notes = "Is Mandatory")
	private String isMandatory;
	private List<Locator> locators;
	@ApiModelProperty(notes = "The StepInput/Parameter actual Value")
	public String actualValue;
	@ApiModelProperty(notes = "The StepInput/Parameter actual Label")
	public String label;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariableSet extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "VARIABLE_SET";

	private String id;
	@NotBlank(message = "variable set name is mandatory")
	@Pattern(message = "variable set name contains special character", regexp = "^[A-Za-z0-9 ]+$")
	private String name;
	private String lastSync;
	private String noOfVariables;
	@NotNull(message = "variableSets is mandatory")
	@Valid
	private List<Variable> variableSets;

}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(value = "variables")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Variable extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "VARIABLE";

	private String id;
	@Size(min = 3, max = 25, message
			= "Name must be between 3 and 25 characters")
	@NotBlank(message = "variable name is mandatory")
	private String name;
	private String varname;
	private String dpName;
	private String colName;
	private String parentType;
	private String subParentId;
	private String value;
	@ApiModelProperty(notes = "true/false. By default it will be false")
	private Boolean encrypt = false;

	private String returningStep;

	@ApiModelProperty(notes = "GLOBAL/LOCAL/DATAPROVIDER/PROJECT ENVIRONMENT VARIABLE")
	@NotBlank(message = "variable type is mandatory")
	private String type;

	private String projectId;

	private String parentVariableType;
	private String parentVariableId;
	private String subParentVariableId;

	private String versionNumber;
	private String lastSync;
	Boolean isSystemVariable = false;

}

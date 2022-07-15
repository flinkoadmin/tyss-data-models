package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParamMap extends BaseEntity{

	private String name;
	private String value;
	private String type;
	private String typeValues;

}

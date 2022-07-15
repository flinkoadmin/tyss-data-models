package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device extends BaseEntity{

	@NotNull(message = "name is mandatory")
	private String name;

	@NotNull(message = "deviceVersion is mandatory")
	private String deviceVersion;

	@NotNull(message = "platform is mandatory")
	private String platform;

	@NotNull(message = "platformVersion is mandatory")
	private String platformVersion;
}

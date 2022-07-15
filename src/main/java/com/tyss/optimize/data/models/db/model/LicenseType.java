package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(value = "license_types")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseType extends BaseEntity {

	@Transient
	public static final String SEQUENCE_NAME = "LICENSE_TYPE";

	@Id
	public String id;

	@NotEmpty(message = "name is mandatory")
	private String name;

	private String desc;

	private String title;

	private boolean published;

	private List<LicensePlan> children;

}

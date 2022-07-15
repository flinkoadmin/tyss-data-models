package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Document(value = "license_plans")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicensePlan extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "LICENSE_PLAN";

	@Id
	public String id;

	@NotEmpty(message = "name is mandatory")
	private String name;

	private String licenseTypeId;
	private String licenseTypeName;
	private String memoryLimit;
	private String memoryUnit;
	private String labelGroupId;
	private String labelGroupName;
	private boolean published;
	private List<ProjectType> projectTypes;

	private List<String> projectIds;

	private List<String> projectTypeIds;

	private List<Limit> featureLimitations;
}

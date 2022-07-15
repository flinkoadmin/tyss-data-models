package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(value = "license_defaults")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LicenseDefault extends BaseEntity {

	@Transient
	public static final String SEQUENCE_NAME = "LICENSE_DEFAULT";

	@Id
	public String id;
	String projectId;
	String name;
	String type;
	String resourceType;
	List<Map<String, String>> valueMap;
	String parentDefaultId;

}

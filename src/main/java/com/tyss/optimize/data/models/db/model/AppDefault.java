package com.tyss.optimize.data.models.db.model;

import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import lombok.Data;

@Data
@Document(value = "app_defaults")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppDefault extends BaseEntity {

	@Id
	public String id;

	String name;
	String type;
	String resourceType;
	List<Map<String, String>> valueMap;
	String parentDefaultId;

}

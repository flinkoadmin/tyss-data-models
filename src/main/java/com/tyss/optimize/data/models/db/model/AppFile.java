package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "files")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppFile extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "FILE";

	@Id
	public String id;
	@Size(min = 2, message = "name must be minimum 2 characters")
	String name;
	String type;
	String relativePath;
	String actualPath;
	String projectId;
	private boolean folder;
	String sheetName;
	double executionOrder;
}

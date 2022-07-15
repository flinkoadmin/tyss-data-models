package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectRole extends BaseEntity{

	private String projectId;
	private String projectName;
	private String roleId;
	private String roleName;
	private String userName;
}

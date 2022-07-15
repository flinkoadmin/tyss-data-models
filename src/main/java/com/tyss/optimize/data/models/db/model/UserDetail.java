package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "user_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetail extends BaseEntity{

	private String id;
	private String name;
	private String globalRoleId;
	private List<ProjectRole> projectRoles;
	private List<String> clientSystemIds;
	private List<QuickRunSetting> quickRunSettings;
	private ClientSystemConfig licenseDefaultMachine;
	private String access;
	@Transient
	private  String privilege;

}

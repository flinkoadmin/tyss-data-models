package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.StorageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Data
@Document(value = "jira")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JiraModel extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "JIRA";
	private String id;
	@NotBlank(message = "server url is mandatory")
	private String serverUrl;
	@NotBlank(message = "domain is mandatory")
	private String domain;
	@NotBlank(message = "user name is mandatory")
	private String userName;
	@NotBlank(message = "account id is mandatory")
	private String accountId;
	@NotBlank(message = "api token is mandatory")
	private String apiToken;

}

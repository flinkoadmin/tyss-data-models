package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.SelectedSystem;
import com.tyss.optimize.data.models.dto.StorageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Document(value = "suite_of_suites")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuiteOfSuites extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "SUITE_OF_SUITES";
	private String id;
	@Pattern(message = "suite of suites name contains special character	", regexp = "^[A-Za-z0-9 ]+$")
	@NotBlank(message = "suite of suites name is mandatory")
	private String name;
	private String description;
	private String projectId;
	private String noOfScript;
	@NotBlank(message = "access is mandatory")
	@ApiModelProperty(notes = "Public / Private ")
	private String access;
	private String runningInstances;
	@NotNull(message = "suites id's are mandatory")
	private List<Suite> suites;
	private Schedule schedule;
	@ApiModelProperty(notes = "Storage details")
	private StorageInfo storageDetails;
	@ApiModelProperty(notes = "List of selected machines")
	private List<SelectedSystem> selectedMachines;
}

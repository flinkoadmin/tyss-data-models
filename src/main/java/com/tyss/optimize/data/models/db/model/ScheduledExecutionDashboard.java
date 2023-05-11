package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "scheduled_execution_dashboard")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduledExecutionDashboard extends BaseEntity {

	@Transient
	public static final String SEQUENCE_NAME = "SCHEDULE";

	@Id
	public String id;
	String projectId;
	@ApiModelProperty(notes = "SUITE / SUITE_OF_SUITE")
	String type;
	String licenseId;
	String name;
	String suiteOfSuiteId;
	String suiteId;
	String status;
	String expiresOn;
	Schedule schedule;
	ExecutionDashboard executionDashboard;
	String executionReferenceId;
	String executionId;
	Suite suite;
	boolean mailSent;
	boolean reScheduled;

}

package com.tyss.optimize.data.models.db.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.StorageInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExecutionResult {

	@ApiModelProperty(notes = "Execution result status - Pass/Fail")
	private String resultStatus;
	
	@ApiModelProperty(notes = "execution status - Running/Pending/Completed/Cancelled/Aborted")
	private String executionStatus;
	
	@ApiModelProperty(notes = "execution id of latest run")
	private String executionId;

	@ApiModelProperty(notes = "Selected Machines")
	private Machines machines;
}

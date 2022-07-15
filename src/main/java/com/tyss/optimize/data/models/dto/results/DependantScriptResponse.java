package com.tyss.optimize.data.models.dto.results;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class DependantScriptResponse {
	
	public String name;
	public String id,key;
	public String status;
	public String ifCheckPointIsFailed;
	public String title;
	public String stepId;
	public Long executionDuration;
	public String  executionDurationInHourMinSecFormat;
	
}

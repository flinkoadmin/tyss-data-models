package com.tyss.optimize.data.models.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionClientReference {

	private String clientId;
	private String executionId;
	private String type;
	private String installedDir;
	private String status;
	private String suiteId;
	private String licenseId;
	
}

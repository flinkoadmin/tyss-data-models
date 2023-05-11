package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuickRunSetting extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "QUICK_RUN_SETTINGS";

	//@Id
	public String qId;
	private String projectId;
	private StepDelay stepDelay;
	private QuickRunSettingResults results;
	private ClientSystemConfig defaultMachine;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
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

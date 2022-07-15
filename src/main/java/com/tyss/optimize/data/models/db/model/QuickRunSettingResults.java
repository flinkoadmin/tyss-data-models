package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuickRunSettingResults {
	private String captureScreenShotStatus;
	private String captureVideoStatus;
	private Boolean showErrorLogs;
	private Boolean showInfoLogs;
	private Boolean makeSettingsDefault;
}

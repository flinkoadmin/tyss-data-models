package com.tyss.optimize.nlp.util;

import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.results.ErrorInfo;
import com.tyss.optimize.data.models.dto.webserviceworkbench.APIExecutionResponseDto;
import com.tyss.optimize.data.models.dto.webserviceworkbench.ApiRequestDto;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

@Data
public class NlpResponseModel {
	@Deprecated(forRemoval = true)
	private IDriver driver;
	private WebDriver webDriver;
	private AndroidDriver androidDriver;
	private IOSDriver iosDriver;
	private String url;

	private Map<String, Object> attributes = new HashMap<>();

	private DesiredCapabilities desiredCapabilities;

	private String message;

	private IfFailed ifCheckPointIsFailed;

	private String status;

	private Long executionTime;

	private ErrorInfo errorInfo;

	private NlpResponseEnum nlp;

	private String returnValue;

	private Boolean logicalConditionStatus;

	private Integer noOfIterations = 0;
	private ApiRequestDto webServiceRequest;
	private APIExecutionResponseDto webserviceResponse;
	private Boolean isWebServiceRequest=Boolean.FALSE;
	
}
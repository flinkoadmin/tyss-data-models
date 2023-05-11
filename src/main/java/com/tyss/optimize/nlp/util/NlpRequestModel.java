package com.tyss.optimize.nlp.util;


import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.StorageInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NlpRequestModel {

	@Deprecated(forRemoval = true)
	private IDriver driver;
	private WebDriver webDriver;
	private AndroidDriver androidDriver;
	private IOSDriver iosDriver;
	private String url;

	private Map<String, Object> attributes = new HashMap<>();

	private DesiredCapabilities desiredCapabilities;

	private String passMessage;

	private String failMessage;

	private String actualFailMessage;

	private StorageInfo storageInfo;

	private String driverPath;

	private boolean headless;
}

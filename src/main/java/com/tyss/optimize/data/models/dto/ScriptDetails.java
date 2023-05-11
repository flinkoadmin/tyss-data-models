package com.tyss.optimize.data.models.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.*;
import java.util.stream.IntStream;

import com.tyss.optimize.data.models.db.model.DataProvider;
import com.tyss.optimize.data.models.db.model.StepInput;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;


@NoArgsConstructor
@Data
@Slf4j
public class ScriptDetails {

	@Deprecated
	private IDriver driver;
	private WebDriver webDriver;
	private AndroidDriver androidDriver;
	private IOSDriver iosDriver;

	private List<StepInput> conditionInput = new ArrayList<>();

	private List<StepInput> parentConditionInput = new ArrayList<>();

	private Map<String, String> localVariables = new HashMap<>();

	private Map<String, Object> stepGroupVariables = new HashMap<>();

	private Map<String, Object> parentStepGroupVariables = new HashMap<>();

	private Map<String, String> projectEnvironmentVariables = new HashMap<>();

	private Map<String, String> globalVariables = new HashMap<>();

	private List<Map<String, String>> logicalCondition = new ArrayList<>();

	private Boolean executeLogicalCondition = false;

	private Boolean checkIncluded = false;

	private Map<String, Integer> loopAttributes = new HashMap<>();

	private Integer numberOfIterations = 0;

	private List<DataProvider> dataProvider = new ArrayList<>();

	private List<DataProvider> dataProviderInfo = new ArrayList<>();

	private Boolean stopExecution=false;

	private Boolean skipIteration=false;

	private Boolean terminateAllIteration=false;
	private Boolean terminateModule=false;
	private Boolean terminateExecution=false;
	private Boolean stopStepGroupExecution=false;
	private Boolean stopDependantExecution=false;
	private Map<String, ArrayList> dataProviderList = new HashMap<>();
	private String logicalStatus = null;

	private Map<String, Object> nlpResponseAttributes = new HashMap<>();
	private String scriptStatus = "PASS";
	private String moduleId;
	private  String androidDeviceSerialNo;
	private  String iosDeviceSerialNo;
	private List<String> systemPort = new ArrayList<>();
	private  Integer selectedSystemPort;

	public void resetVariables(){
		driver=null;
		webDriver=null;
		androidDriver=null;
		iosDriver=null;
		conditionInput = new ArrayList<>();
		localVariables = new HashMap<>();
		stepGroupVariables = new HashMap<>();
		logicalCondition = new ArrayList<>();
		executeLogicalCondition = false;
		loopAttributes = new HashMap<>();
		numberOfIterations = 0;
		dataProvider = new ArrayList<>();
		dataProviderInfo = new ArrayList<>();
		dataProviderList = new HashMap<>();
		nlpResponseAttributes = new HashMap<>();
		scriptStatus = "PASS";
		logicalStatus = null;
		androidDeviceSerialNo=null;
		iosDeviceSerialNo=null;
	}

}


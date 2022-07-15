package com.tyss.optimize.data.models.dto;

import java.util.*;
import com.tyss.optimize.data.models.db.model.DataProvider;
import com.tyss.optimize.data.models.db.model.StepInput;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
@Data
public class ScriptDetails {

	IDriver driver;
	WebDriver webDriver;
	AndroidDriver androidDriver;
	IOSDriver iosDriver;
	
	List<StepInput> conditionInput = new ArrayList<StepInput>();

	List<StepInput> parentConditionInput = new ArrayList<>();

	Map<String, String> localVariables = new HashMap<String, String>();

	Map<String, Object> stepGroupVariables = new HashMap<String, Object>();
	
	List<Map<String, String>> logicalCondition = new ArrayList<Map<String, String>>();
	
	Boolean executeLogicalCondition = false;

	Boolean checkIncluded = false;
	
	Map<String, Integer> loopAttributes = new HashMap<String, Integer>();
	
	Integer numberOfIterations = 0;
	
	List<DataProvider> dataProvider = new ArrayList<DataProvider>();
	
	List<DataProvider> dataProviderInfo = new ArrayList<DataProvider>();
	
	Boolean stopExecution=false;

	Boolean skipIteration=false;
	
	Boolean terminateAllIteration=false;
	Boolean terminateModule=false;
	Boolean terminateExecution=false;
	Boolean stopStepGroupExecution=false;
	Boolean stopDependantExecution=false;
	Map<String, ArrayList> dataProviderList = new HashMap<String, ArrayList>();
	String logicalStatus = null;
	//String systemId = "";
	//String deviceSerialNo = "";
	Map<String, Object> nlpResponseAttributes = new HashMap<String, Object>();
	String scriptStatus = "PASS";
	String moduleId;
	public String androidDeviceSerialNo;
	public String iosDeviceSerialNo;
	List<String> systemPort = Arrays.asList("8201", "8202", "8203", "8204" , "8205", "8206", "8207", "8208", "8209", "8210");

	public void resetVariables(){
		 driver=null;
		 webDriver=null;
		 androidDriver=null;
		 iosDriver=null;
		 conditionInput = new ArrayList<StepInput>();
		 localVariables = new HashMap<String, String>();
		 stepGroupVariables = new HashMap<String, Object>();
		 logicalCondition = new ArrayList<Map<String, String>>();
		 executeLogicalCondition = false;
		 loopAttributes = new HashMap<String, Integer>();
		 numberOfIterations = 0;
		 dataProvider = new ArrayList<DataProvider>();
		 dataProviderInfo = new ArrayList<DataProvider>();
		 dataProviderList = new HashMap<String, ArrayList>();
		 //systemId = "";
		 // deviceSerialNo = "";
		 nlpResponseAttributes = new HashMap<String, Object>();
		 scriptStatus = "PASS";
		 logicalStatus = null;
		 String androidDeviceSerialNo=null;
		 String iosDeviceSerialNo=null;
	}

}


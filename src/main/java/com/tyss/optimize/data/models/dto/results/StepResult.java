package com.tyss.optimize.data.models.dto.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tyss.optimize.data.models.db.model.Element;
import com.tyss.optimize.data.models.db.model.TagDetails;
import com.tyss.optimize.data.models.dto.ReferenceDetails;
import com.tyss.optimize.data.models.db.model.StepInput;
import com.tyss.optimize.data.models.dto.TagDetailsDTO;
import com.tyss.optimize.data.models.dto.webserviceworkbench.APIExecutionResponseDto;
import com.tyss.optimize.data.models.dto.webserviceworkbench.ApiRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepResult {
    public String id,key;
    public String name;
    public String title;
    public String type;//step,stepgroup,loop
    //public List<Condition> preConditions;
    public InputData inputData;
    public List<StepResult> stepResults;
   // public List<Condition> postConditions;
    //public String stepId;
    public String status;
    public String message;
public List<StepInput> stepInputs;
    public List<Element> elementDetails;
    public Map<String,Object> nlpResponse;
    public Long executionDuration;
    public String  executionDurationInHourMinSecFormat;
    public String screenshotPath;
    public ErrorInfo errorLog;
    public Infolog infolog;
    public ResponseObject responseObject;
//    public Boolean terminate=false;
    public Integer iteration;
	public ReferenceDetails stepReturn;
    public ExecutionStatistics stepResultStats;
    public String executedOn;
    private boolean folder=false;
    private List<?> children;
    public String cascaded;
    public String cascadedFrom;
    public String stepId;
    public String toolTip;
    ApiRequestDto webServiceRequest;
    APIExecutionResponseDto webserviceResponse;
    Boolean isWebServiceRequest=Boolean.FALSE;
    private List<TagDetailsDTO> tagDetailsDTOs;
    private String nlpName;

}

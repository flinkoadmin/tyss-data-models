package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Element extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ELEMENT";
    @ApiModelProperty(notes = "The details of Dynamic Locator")
    public List<StepInput> dynamicLocatorArray;
    double executionOrder;
    @ApiModelProperty(notes = "The Element Id")
    @Id
    private String id;
    @Size(min = 2, message = "Name must have 2 or more characters")
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    private String name;
    @Size(max = 200, message = "Description must be 200 characters")
    private String desc;
    @NotNull(message = "type is mandatory")
    private String type;
    private int locatorsCount;
    @ApiModelProperty(notes = "The details of Locator")
    private List<Locator> locators;
    private String isShared;
    private String isRecorded;
    private boolean folder;
    private Map<String, String> location;
    private String platform;
    private String projectType;
    private String projectId;
    private String elementId;
    private List<String> users;
    private List<String> excludeUsers;
    private List<JSONObject> deleteUsers;
    private boolean commit = false;
    private boolean publish = false;
    private boolean newState = false;
    private boolean activeStatus;
    private String version;
    private boolean updatedStatus;
}


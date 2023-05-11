package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "recorded_elements")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordedElement extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "RECORDED_ELEMENT";

    @ApiModelProperty(notes = "The Element Id")
    @Id
    private String id;
    private String name;
    private String type;
    private String desc;
    private int locatorsCount;
    @ApiModelProperty(notes = "The details of Locator")
    private List<Locator> locators;
    @ApiModelProperty(notes = "The details of Dynamic Locator")
    public List<StepInput> dynamicLocatorArray;
    private String isShared;
    private String platform;
    private String projectType;
    private String isRecorded;
    private boolean folder;
    private Object  listOfPages;
    @NotNull(message = "projectId is mandatory")
    @NotBlank(message = "projectId must not be blank")
    private String projectId;
    private String expiryDate;
    private int totalElementCount;
    private boolean commit=false;
    private boolean publish=false;
    private boolean newState = false;

    private String elementId;
    private List<String> users;
    private List<String> excludeUsers;
    private List<JSONObject> deleteUsers;
    private boolean activeStatus;
    private String version;
    private boolean updatedStatus;
}

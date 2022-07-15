package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "shared_elements")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SharedElement extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "SHARED_ELEMENT";

    @ApiModelProperty(notes = "The Element Id")
    @Id
    private String id;
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    @Size(max=100,min=2,message="Name must be between 2 and 100 characters, criteria not met")
    private String name;
    private String desc;
    @NotNull(message = "type is mandatory")
    private String type;
    private int locatorsCount;
    @ApiModelProperty(notes = "The details of Locator")
    private List<Locator> locators;
    @ApiModelProperty(notes = "The details of Dynamic Locator")
    public List<StepInput> dynamicLocatorArray;
    public List<String> parentIds =new ArrayList<>();
    private Object[] pageIdsNames;
    private String isShared;
    private String isRecorded;
    private boolean folder;
    private String platform;
    @NotNull(message = "projectType is mandatory")
    @NotBlank(message = "projectType must not be blank")
    private String projectType;
    @NotNull(message = "projectId is mandatory")
    @NotBlank(message = "projectId must not be blank")
    private String projectId;
    private int totalCount;
    private boolean commit=false;
    private boolean publish=false;
    private boolean newState = false;

}

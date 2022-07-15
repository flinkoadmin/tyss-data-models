package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.LocatorStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Locator {

    @ApiModelProperty(notes = "The value of Locator")
    private String value;

    @ApiModelProperty(notes = "The type of Locator : static/dynamic")
    private String type;

    @ApiModelProperty(notes = "The name of Locator : xpath, name, classname, id etc")
    private String name;

    @ApiModelProperty(notes = "The reference of Locator : LOCAL/GLOBAL")
    private String reference;

    @ApiModelProperty(notes = "The status of Locator : USED,NOT_FOUND,NOT_USED")
    private LocatorStatus status;

    @ApiModelProperty(notes = "The details of Dynamic Locator")
    private List<StepInput> dynamicLocatorArray;

    private String isRecorded;

    private String priority;

    private String actualValue;

    private String dynamicInputValue;
    boolean defaultAddedFirst;
    boolean defaultAddedSecond;
}

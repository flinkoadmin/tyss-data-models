package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.Element;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PagePublishedDataReq {

    @NotNull(message = "element should not be null")
    List<Element> elements;
    @NotNull(message = "action should not be null")
    String action;
    @NotNull(message = "fromState should not be null")
    String fromState;
    String status;
}

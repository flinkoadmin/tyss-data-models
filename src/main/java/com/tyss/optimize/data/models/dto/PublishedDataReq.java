package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.Element;
import lombok.Data;

@Data
public class PublishedDataReq {

    String pageId;
    Element element;
    String action;
    String fromState;
    String status;
}

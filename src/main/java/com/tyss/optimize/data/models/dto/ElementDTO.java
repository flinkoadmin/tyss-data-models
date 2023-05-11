package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDTO {

    private Element element;
    private String prevState;
    private String action;
    private String status;

}

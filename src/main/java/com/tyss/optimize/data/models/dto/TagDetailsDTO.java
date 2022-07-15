package com.tyss.optimize.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDetailsDTO {

    private String id;
    private String name;
    private String description;
    private String type;
    private Boolean updateEveryWhere;
    private Boolean updateAllSteps;

}

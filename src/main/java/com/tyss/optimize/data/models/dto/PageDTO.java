package com.tyss.optimize.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {

    private String pageId;
    List<ElementDTO> elements;
    private String nextState;

}

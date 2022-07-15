package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SheetData extends BaseEntity {


    @ApiModelProperty(notes = "XLS sheet name")
    String sheetName;

    @ApiModelProperty(notes = "XLS sheet fromRow")
    String fromRow;

    @ApiModelProperty(notes = "XLS sheet toRow")
    String toRow;

    @ApiModelProperty(notes = "XLS sheet header")
    List<String> header;


}

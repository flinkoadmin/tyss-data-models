package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.DataProviderColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataProvider extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "DATA_PROVIDER";
    String id;
    @NotNull(message = "name is mandatory")
    String name;
    @NotNull(message = "type is mandatory")
    String type;
    List<Step> steps;
    @NotNull(message = "fileId is mandatory")
    String fileId;
    @NotNull(message = "sheetName is mandatory")
    String sheetName;
    @NotNull(message = "fromRow is mandatory")
    String fromRow;
    @NotNull(message = "toRow is mandatory")
    String toRow;
    String iterationType;
    public List<DataProviderColumn> columns;
    public List<Map<String, String>> dataProviderList;
    public int index;
    boolean includePrePostCondition;
    List<SheetData> sheetdata;
    List<Variable> variables;
    String excelSheetData;
    String url;


}

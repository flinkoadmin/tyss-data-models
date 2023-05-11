package com.tyss.optimize.data.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import java.util.List;

@Data
@NoArgsConstructor
public class GridFSResponseDTO {
    private int responseCode;
    private JSONObject jsonObject;
    private String message;
    private String objectId;
    private List<JSONObject> jsonObjectList;
    private List<Object> objectList;
}

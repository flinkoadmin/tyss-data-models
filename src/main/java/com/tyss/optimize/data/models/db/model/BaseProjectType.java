package com.tyss.optimize.data.models.db.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(value = "base_project_types")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseProjectType extends  BaseEntity{

    private String  id;
    private String limitId;
    private String name;
    private String limit;
    private String projectTypeId;
    private String type;
    private String moduleName;
    private List<JSONObject> limits;

}

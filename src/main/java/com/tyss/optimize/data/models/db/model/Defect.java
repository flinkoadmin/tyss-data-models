package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(value = "defects")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Defect{

    @Id
    String id;
    String projectId;
    String description;
    String defectTemplateId;
    String defectLifeCycleTemplateId;
    String defectTemplateName;
    Map<String, Object> defectDetails;
    String state;
    List<Object> state_transitions;
    List<Comments> comments;
}

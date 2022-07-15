package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "step_group_nlps")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepGroupNlp extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "STEP_GROUP_NLP";
	
	@Id
    public String id;
	String name;
	String nlpName;
	String projectId;
	String passMessage;
	String failMessage;
	Boolean isChanged;
	String nlpType;
	List<StepInput> stepInputs = new ArrayList<>();
	String platform;
	String libraryId;
	String returnType;
	String toolTip;
	String displayName;
}

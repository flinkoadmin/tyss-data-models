package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "nlps")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nlp extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "NLP";
	
	@Id
    public String id;
	String searchName;
	String nlpName;
	String nlpType;
	String packageName;
	String displayName;
	String toolTip;
	String description;
	// IOS / ANDROID / WEB
	String platform;
	String passMessage;
	String failMessage;
	String actualFailedResult;
	List<StepInput> stepInputs;
	String returnType;
}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "program_elements_nlps")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramElementNlp extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "PE_NLP";
	
	@Id
    public String id;
	String programElementId;
	String projectId;

	@NotNull(message = "name is mandatory")
	@NotBlank(message = "name must not be blank")
	@Size(min = 1, message = "Name must have 1 or more characters")
	String name;

	@NotNull(message = "Description is mandatory")
	@NotBlank(message = "Description must not be blank")
	@Size(min = 1, message = "Description must have 1 or more characters")
	String desc;

	@NotNull(message = "nlpName is mandatory")
	@NotBlank(message = "nlpName must not be blank")
	@Size(min = 1, message = "nlpName must have 1 or more characters")
	String nlpName;

	@NotNull(message = "passMessage is mandatory")
	@NotBlank(message = "passMessage must not be blank")
	@Size(min = 1, message = "passMessage must have 1 or more characters")
	String passMessage;

	@NotNull(message = "failMessage is mandatory")
	@NotBlank(message = "failMessage must not be blank")
	@Size(min = 1, message = "failMessage must have 1 or more characters")
	String failMessage;

	Boolean isChanged;
	String nlpType;
	String returnType;
	List<StepInput> stepInputs;
	String toolTip;
	boolean isNonPE;
}

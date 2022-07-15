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
@Document(value = "libraries")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Library extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "LIBRARY";

	@Id
	public String id;
	@NotNull(message = "name is mandatory")
	@NotBlank(message = "name must not be blank")
	@Size(min = 3, max = 100, message
			= "Name must be between 3 and 100 characters")
	String name;
	@Size(max = 200,message ="Description must be 200 characters" )
	String desc;
	String projectId;
	double executionOrder;
	List<StepGroup> stepGroups;
	int subLibraryCount;
	int stepGroupCount;
	boolean expanded;
	boolean folder=true;
	boolean defaultLibrary=false;
}

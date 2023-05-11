package com.tyss.optimize.data.models.db.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "modules")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Module extends BaseEntity{
	
	@Transient
    public static final String SEQUENCE_NAME = "MODULE";

	@Id
	@JsonAlias("_id")
    public String id;

	@NotNull(message = "name is mandatory")
	@NotBlank(message = "name must not be blank")
	String name;
	@Size(max = 200,message ="Description must be 200 characters" )
	String desc;
	String projectId;

	double executionOrder;
	List<Script> scripts;
	List<Conditions> conditions;
	int subModuleCount;
	int hierarchy;
	int scriptCount;
	int preConditionCount;
	int postConditionCount;
	boolean expanded;
	boolean folder=true;

}

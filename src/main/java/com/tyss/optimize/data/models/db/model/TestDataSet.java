package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDataSet extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "TEST_DATA_SET";

	private String id;
	@NotBlank(message = "Test data set name is mandatory")
	@Pattern(message = "Test data set name contains special character", regexp = "^[A-Za-z0-9 ]+$")
	private String name;

	private String lastSync;
	private String noOfFiles;
	@NotNull(message = "test data sets is mandatory")
	@Valid
	private List<Object> testDataSets;



}

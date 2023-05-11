package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.dto.FileDTO;
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
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "folders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Folder extends BaseEntity {
	
	@Transient
    public static final String SEQUENCE_NAME = "FOLDER";
	
	@Id
    public String id;
	@Size(min = 2, message = "Name must have 2 or more characters")
	@NotNull(message = "name is mandatory")
	@NotBlank(message = "name must not be blank")
	String name;
	@Size(max = 200,message ="Description must be 200 characters" )
	String desc;
	String projectId;
	Set<String> files;
	int subFolderCount;
	int fileCount;
	boolean expanded;
	boolean folder=true;
	double executionOrder;
	boolean defaultFolder=false;
	List<FileDTO> fileDTOList;
	List<AppFile> appFiles;
}

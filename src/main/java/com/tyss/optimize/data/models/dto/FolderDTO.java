package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderDTO extends BaseEntity {
    @Size(min = 3, max = 25, message
            = "name must be between 3 and 25 characters")
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
}

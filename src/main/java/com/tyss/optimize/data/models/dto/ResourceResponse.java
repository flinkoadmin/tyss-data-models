package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.Module;
import com.tyss.optimize.data.models.db.model.*;
import lombok.Data;

import java.util.List;

@Data
public class ResourceResponse {
    int statusCode;
    String message;
    int responseCode;
    String status;
    List<Variable> variables;
    List<Page> projectElements;
    List<PackageModel> programElements;
    List<Library> stepGroups;
    List<Module> scripts;
    List<FolderDTO> testData;
    List<FileDTO> files;
    String bucketName;
    String type;
}

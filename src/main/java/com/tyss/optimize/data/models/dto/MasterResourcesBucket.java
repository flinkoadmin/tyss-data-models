package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.Module;
import com.tyss.optimize.data.models.db.model.*;
import lombok.Data;

import java.util.List;

@Data
public class MasterResourcesBucket {
    private String bearerToken;
    private String bucketName;
    private List<Variable> variables;
    private List<Page> projectElements;
    private List<FolderDTO> testData;
    private List<FileDTO> files;
    private List<PackageModel> programElements;
    private List<Library> stepGroups;
    private List<Module> scripts;
    private String type;
}

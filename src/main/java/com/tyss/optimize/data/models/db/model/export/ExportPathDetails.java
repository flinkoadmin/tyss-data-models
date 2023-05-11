package com.tyss.optimize.data.models.db.model.export;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExportPathDetails {
    String parentFolderName;
    String parentFolderPath;
    String zipFolderName;
    String zipFileName;
    String projectFolderName;
    String pageFolderName;
    String sharedElementFolderName;
    String variableFolderName;
    String libraryFolderName;
    String stepGroupNlpFolderName;
    String testDataFolderName;
    String moduleFolderName;
    String moduleConditionFolderName;
    String suiteFolderName;
    String defectsFolderName;
    String testCaseFolderName;
    String defectTemplateFolderName;
    String defectLifeCycleTemplateFolderName;
    String unzipParentFolderName;
    String unzipParentFolderPath;

}

package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "history_configs")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class HistoryConfiguration extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "HISTORY_CONFIGURATION";

    @Id
    public String id;
    private Integer noOfProjectElements;
    private Integer noOfProgramElements;
    private Integer noOfStepGroups;
    private Integer noOfTestData;
    private Integer noOfGlobalVariables;
    private Integer noOfTestScripts;
    private Integer noOfModules;
    private Integer noOfPages;
    private Integer noOfPackages;
    private Integer noOfLibraries;
    private Integer noOfFolders;
    private Integer noOfSuites;
    private Integer noOfSuiteOfSuites;
    private Integer noOfWebServices;
    private Integer noOfDataBases;
    private Integer noOfHistory;
    private String projectId;
}

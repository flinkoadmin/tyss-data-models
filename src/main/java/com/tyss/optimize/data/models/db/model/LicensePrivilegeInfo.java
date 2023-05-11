package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class LicensePrivilegeInfo {
    private LicensePrivilegeProjectInfo project;
    private LicensePrivilegeRepositoryInfo repository;
    private LicensePrivilegeTestDev testDev;
    private LicensePrivilegeDefects defects;
    private LicensePrivilegeExecutionInfo execution;
}

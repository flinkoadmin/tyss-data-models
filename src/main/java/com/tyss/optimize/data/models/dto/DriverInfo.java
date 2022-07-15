package com.tyss.optimize.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
Created By Author Nagalla Anil Kumar
Created on 12-01-2022 18:37
*/



@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverInfo {
    private String browserName;
    private String driverName;
    private String driverVersion;
    private String sourcePath;
    private String destinationPath;
    private String osName;
}
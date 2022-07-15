package com.tyss.optimize.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverMapping {
    private String driverVersion;
    private String sourcePath;
    private List<String> driverSupportedBrowserVersion;
}

package com.tyss.optimize.data.models.db.model;

import lombok.Data;

import java.util.List;

@Data
public class MobileSearchFilter {
    private List<String> os;
    private List<String> osVersion;
    private List<String> browser;
    private List<String> browserVersion;
    private List<String> deviceBrand; 
    private List<String> deviceName;
    private List<String> deviceType;
}

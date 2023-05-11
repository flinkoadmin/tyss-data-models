package com.tyss.optimize.data.models.db.model;

import lombok.Data;

@Data
public class DeviceInfo {
    private String version;
    private String brand;
    private String model;
    private String name;
    private String fullName;
    private String serial_no;
    private String type;
    private String subType;
    private String platform;
    private String driverVersion;
    private String previousBrowserVersion;
    public String appId;
    public String appName;
}

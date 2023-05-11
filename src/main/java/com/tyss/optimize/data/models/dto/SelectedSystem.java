package com.tyss.optimize.data.models.dto;

import com.tyss.optimize.data.models.db.model.DeviceInfo;
import com.tyss.optimize.data.models.db.model.MachineInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedSystem {
    @ApiModelProperty(notes = "The System Type : local , browserStack etc")
    public String executionEnv;
   /* @ApiModelProperty(notes = "The System Name")
    public String systemName;*/
    @ApiModelProperty(notes = "The Browser Name : chrome, firefox etc")
    public String browserName;
    @ApiModelProperty(notes = "The Browser Name : chrome, firefox etc")
    public DeviceInfo browserInfo;
    @ApiModelProperty(notes = "Browser version")
    public String browserVersion;
    @ApiModelProperty(notes = "The System Url : http://localhost:4444/wd/hub")
    public String systemUrl;
    @ApiModelProperty(notes = "The Device Url : http://localhost:4723/wd/hub")
    public String deviceUrl;
	@ApiModelProperty(notes = "SystemId")
    public String systemId;
    @ApiModelProperty(notes = "version")
    public String version;
    @ApiModelProperty(notes = "MachineInfos")
    public MachineInfo machineInfo;
    @ApiModelProperty(notes = "DeviceInfos")
    public List<DeviceInfo> deviceInfo;//max 2 devices android,iOS
    @ApiModelProperty(notes = "DeviceInfos")
    public String clientId;
    @ApiModelProperty(notes = "Client Status")
    public String status;
    @ApiModelProperty(notes = "Client access")
    public String access;
    private boolean headless;
}

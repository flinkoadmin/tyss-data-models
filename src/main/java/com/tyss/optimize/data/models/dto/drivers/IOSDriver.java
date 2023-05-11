package com.tyss.optimize.data.models.dto.drivers;


import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.ScriptDetails;
import org.springframework.stereotype.Component;

@Component(value = "iosDriver")
@Deprecated
public class IOSDriver implements IDriver {

    private io.appium.java_client.ios.IOSDriver iosDriver;

    public IOSDriver() {
    }

    public IOSDriver(io.appium.java_client.ios.IOSDriver iosDriver) {
        this.iosDriver = iosDriver;
    }

    @Override
    public void setIDriver(ScriptDetails scriptDetails) {
    	scriptDetails.setDriver(new IOSDriver(this.iosDriver));
    }

    @Override
    public Object getSpecificIDriver() {
        return this.iosDriver;
    }

}

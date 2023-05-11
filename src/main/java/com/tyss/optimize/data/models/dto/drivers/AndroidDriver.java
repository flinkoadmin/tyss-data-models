package com.tyss.optimize.data.models.dto.drivers;


import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.ScriptDetails;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class AndroidDriver implements IDriver {

    private io.appium.java_client.android.AndroidDriver androidDriver;

    public AndroidDriver() {
    }

    public AndroidDriver(io.appium.java_client.android.AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    @Override
    public void setIDriver(ScriptDetails scriptDetails) {
    	scriptDetails.setDriver(new AndroidDriver(this.androidDriver));
    }

    @Override
    public Object getSpecificIDriver() {
        return this.androidDriver;
    }

}

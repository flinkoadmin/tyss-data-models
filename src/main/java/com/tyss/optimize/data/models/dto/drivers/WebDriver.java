package com.tyss.optimize.data.models.dto.drivers;


import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.ScriptDetails;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class WebDriver implements IDriver {


    private org.openqa.selenium.WebDriver webDriver;

    public WebDriver() {
    }

    public WebDriver(org.openqa.selenium.WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void setIDriver(ScriptDetails scriptDetails) {
    	scriptDetails.setDriver(new WebDriver(this.webDriver));
    }

    @Override
    public Object getSpecificIDriver() {
        return this.webDriver;
    }


}

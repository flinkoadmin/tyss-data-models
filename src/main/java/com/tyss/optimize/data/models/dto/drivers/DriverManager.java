package com.tyss.optimize.data.models.dto.drivers;


import com.tyss.optimize.data.models.dto.IDriver;
import com.tyss.optimize.data.models.dto.ScriptDetails;
import org.springframework.stereotype.Component;

@Component
public class DriverManager {

    private IDriver driver;

    public DriverManager() {
    }

    public DriverManager(IDriver driver) {
        this.driver = driver;
    }

    public void setDriver(ScriptDetails scriptDetails){
        driver.setIDriver(scriptDetails);
    }


}

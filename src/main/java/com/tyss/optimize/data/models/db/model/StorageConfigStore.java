package com.tyss.optimize.data.models.db.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageConfigStore {

    private Map<String, String> folderPath;

}




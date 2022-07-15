package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/*
Created By Author Nagalla Anil Kumar
Created on 01-02-2022 15:29
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "ClientDriverConfig")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverConfig extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "DRIVER_CONFIG";

    @Id
    private String id;
    private String driverName;
    private String browserName;
    private List<DriverMapping> driverVersionSourcePath;
    private String destinationPath;
    private String osName;
    private String currentBrowserVersion;
    private String currentDriverVersion;

}

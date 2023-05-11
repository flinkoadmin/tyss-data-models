package com.tyss.optimize.data.models.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientStatus {

    private String clientStatus;
    private String webserviceStatus;
    private String syncStatus;
    private String appiumStatus;
    private String nodeStatus;
    private String status;
    private String message;
    private String hostName;
}

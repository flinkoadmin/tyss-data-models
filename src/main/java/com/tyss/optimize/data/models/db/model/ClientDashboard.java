package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDashboard {

    List<ClientDashboard> client;
    String osName;
    String name;
    String osVersion;
    String status;
    String access;
    List<Browser> browsers;
    List<Browser> devices;
    Long count;
    List<ClientDashboard> osVersions;
    List<ClientDashboard> accessDetails;
}

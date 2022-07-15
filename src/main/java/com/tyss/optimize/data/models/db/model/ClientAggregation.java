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
public class ClientAggregation {

    String type;
    long count;
    List<ClientDashboard> osVersion;
    List<ClientDashboard> osDetails;
    List<Browser> browserDetails;
    List<ClientDashboard> status;
    List<ClientDashboard> access;
    List<Browser> devices;

}

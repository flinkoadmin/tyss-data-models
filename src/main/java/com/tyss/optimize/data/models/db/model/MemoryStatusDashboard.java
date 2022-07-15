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
public class MemoryStatusDashboard {

    String id;
    List<MemoryStatusDashboard> memory;
    String type;
    String assignedMemory;
    String usedMemory;
    String remainingMemory;
    String name;
    Long noOfProjects;
    String date;
    List<MemoryStatusDashboard> projectDetails;
    MemoryStatusDashboard testDataFiles;
    MemoryStatusDashboard screenShots;
    MemoryStatusDashboard videos;
    MemoryStatusDashboard externalLibraries;
    MemoryStatusDashboard totalConsumption;
    MemoryStatusDashboard individualConsumption;
    String totalMemory;
}

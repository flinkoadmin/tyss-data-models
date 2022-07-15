package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "memory_config")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MemoryConfig extends BaseEntity{
    @Id
    private String id;
    private String licenseId;
    private Double totalMemory;
    private String totalMemoryUnit;
    private Double usedMemory;
    private String usedMemoryUnit;
    private Double availableMemory;
    private String availableMemoryUnit;
    private Integer projectCount;
    private List<Project> projects;
}

package com.tyss.optimize.data.models.db.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "packages")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PackageModel extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "PACKAGE";

    @Id
    public String id;

    @Size(min = 1, message = "Name must have 1 or more characters")
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    String name;
    @Size(max = 200,message = "Description must be 200 characters" )
    String desc;
    String projectId;
    List<ProgramElement> programElements;
    double executionOrder;
    int subPackageCount;
    int programElementCount;
    boolean expanded;
    boolean folder=true;
}

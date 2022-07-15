package com.tyss.optimize.data.models.db.model;

import java.util.ArrayList;
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
@Document(value = "pages")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Page extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "PAGE";

    @Id
    public String id;

    @Size(min = 2, max = 100, message
            = "Name must be between 2 and 100 characters")
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    String name;
    @Size(max = 200,message ="Description must be 200 characters" )
    String desc;
    String projectId;
    String projectType;
    String platform;
    List<Element> elements;
    int subPageCount;
    int elementCount;
    boolean expanded;
    boolean folder=true;
    double executionOrder;
    boolean commit=false;
    boolean publish=false;
    boolean newState = false;

}

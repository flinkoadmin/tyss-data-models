package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "saucelabs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SauceLabs extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "SAUCELABS";

    @Id
    public String id;

    @Size(max = 100,min=2,message = "Instance Name must be 2 and 100 character")
    @NotNull(message = "Instance Name is mandatory")
    @NotBlank(message = "Instance Name must not be blank")
    private String instanceName;

    @NotNull(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Access key is mandatory")
    private String accessKey;

    private List<String> dataCenters;

    private String defaultDataCenter;
}

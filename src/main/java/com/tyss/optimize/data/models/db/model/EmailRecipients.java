package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailRecipients extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "EMAIL_RECIPIENT";

    private String id;
    //@Pattern(message = "Email recipient name contains special character", regexp = "^[A-Za-z0-9 ]+$")
    //@NotBlank(message = "Email recipient name is mandatory")
    private String name;
    @NotBlank(message = "Email id is mandatory")
    @Pattern(message = "Please Provide valid mail in Email group",regexp= "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")
    private String emailId;
    private String groupName;

}

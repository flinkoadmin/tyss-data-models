package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "email_groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailGroup extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "EMAIL_GROUP";

    @Id
    String id;
    //@Pattern(message = "Email group name contains special character", regexp = "^[A-Za-z0-9 ]+$")
    //@NotBlank(message = "Email group name can not be blank")
    //@NotNull(message = "Email group name is mandatory")
    private String name;
    private String projectId;
    //@Valid
    @NotNull(message = "emailUserList is mandatory")
    private List<EmailRecipients> emailUserList;

}

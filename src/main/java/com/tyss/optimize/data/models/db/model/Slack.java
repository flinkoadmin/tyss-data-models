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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "slack")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Slack extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "SLACK";

    @Id
    public String id;

    @Size(max = 100,min=2,message = "Workspace name must be 2 and 100 character")
    @NotNull(message = "Workspace name is mandatory")
    @NotBlank(message = "Workspace name must not be blank")
    private String workspaceName;

    @Size(max = 100,min=2,message = "Channel Name must be 2 and 100 character")
    @NotNull(message = "Channel Name is mandatory")
    @NotBlank(message = "Channel Name must not be blank")
    private String channelName;

    @NotNull(message = "Webhooks Url is mandatory")
    @NotBlank(message = "Webhooks Url must not be blank")
    private String webhooksUrl;

    private String text;



}

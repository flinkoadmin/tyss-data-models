package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.common.util.ProjectStatusTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Document(value = "projects")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "PROJECT";

    @Id
    String id;

    @Size(min = 2, max = 50, message
            = "name must be between 2 and 50 characters")
    String name;
    @Size(min = 2, max = 150, message
            = "desc must be between 2 and 150 characters")
    @NotNull(message = "desc is mandatory")
    String desc;
    @NotNull(message = "type is mandatory")
    String type;
    String defaultTemplateId;
    String status = ProjectStatusTypes.STATUS_OPEN;
    String platform;
    String url;
    String appActivity;
    @NotNull(message = "assignedMemory is mandatory")
    String assignedMemory;
    String consumedMemory;
    String appPackage;
    List<FileDetail> apkFiles;
    List<FileDetail> ipaFiles;
    String appType;
    String bundleId;
    String port;
    String baseUri;
    Long numberOfUsers;

}

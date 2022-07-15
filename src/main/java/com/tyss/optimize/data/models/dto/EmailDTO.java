package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tyss.optimize.data.models.db.model.AppFile;
import com.tyss.optimize.data.models.db.model.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDTO extends BaseEntity {

    @Email(message = "provide valid email")
    @NotNull(message = "emailTemplateId is mandatory")
    private String toEmail;

    private String subject;

    @Email(message = "provide valid email")
    private String fromEmail;

    private String[] ccEmails;

    private String[] bccEmails;

    @NotNull(message = "emailTemplateId is mandatory")
    private String emailTemplateId;

    private String name;

    private String type;

    private Map<String, Object> valueMap;

    @Transient
    List<MultipartFile> multipartFiles;

    @Transient
    List<AppFile> files;
}

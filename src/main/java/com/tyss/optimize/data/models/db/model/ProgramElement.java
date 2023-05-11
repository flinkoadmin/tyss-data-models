package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramElement extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "PROGRAM_ELEMENT";
    @Transient
    public String compilationError;
    double executionOrder;
    private String id;
    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name must not be blank")
    @Size(min = 1, message = "Name must have 1 or more characters")
    private String name;
    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description must not be blank")
    @Size(min = 1, message = "Description must have 1 or more characters")
    private String desc;
    @NotNull(message = "packageName is mandatory")
    private String packageName;
    @NotNull(message = "nlpName is mandatory")
    @NotBlank(message = "nlpName must not be blank")
    @Size(min = 1, message = "nlpName must have 1 or more characters")
    private String nlpName;
    @NotNull(message = "code is mandatory")
    private String code;
    @NotNull(message = "passMessage is mandatory")
    @NotBlank(message = "passMessage must not be blank")
    @Size(min = 1, message = "passMessage must have 1 or more characters")
    private String passMessage;
    @NotNull(message = "failMessage is mandatory")
    @NotBlank(message = "failMessage must not be blank")
    @Size(min = 1, message = "failMessage must have 1 or more characters")
    private String failMessage;
    @NotNull(message = "language is mandatory")
    private String language;
    private String langVersion;
    private boolean folder;
    private String toolTip;
    private boolean nonPE;
    private String peNlpId;

}

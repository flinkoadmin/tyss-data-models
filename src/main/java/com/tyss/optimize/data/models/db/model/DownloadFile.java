package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DownloadFile {
    @NotNull(message = "fileId is mandatory")
    private String fileId;

    @NotNull(message = "downloadPath is mandatory")
    private String downloadPath;
}

package com.tyss.optimize.data.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileMapperDTO {
    private MultipartFile file;
    private String location;
}

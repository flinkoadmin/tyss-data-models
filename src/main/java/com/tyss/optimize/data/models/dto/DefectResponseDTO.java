package com.tyss.optimize.data.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DefectResponseDTO extends ResponseDTO {
    private  boolean lastPage;
}

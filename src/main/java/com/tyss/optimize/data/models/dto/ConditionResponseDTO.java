package com.tyss.optimize.data.models.dto;


import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
public class ConditionResponseDTO extends ResponseDTO {
    private int pageNumber;
    private int totalPageCount;
    private int totalDocuments;
    private  boolean firstPage;
    private boolean previousPage;
    private boolean nextPage;
    private  boolean lastPage;

}

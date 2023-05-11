package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailRequest extends EmailDTO {

    private String role;
    private String message;
    private String productName;
    private String phoneNumber;
    private String companyName;
    private String customerName;

}

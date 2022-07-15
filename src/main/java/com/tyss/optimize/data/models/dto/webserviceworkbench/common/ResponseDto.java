package com.tyss.optimize.data.models.dto.webserviceworkbench.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Object success;
    private ErrorResponse errors;

    public boolean hasErrors() {
        return errors != null;
    }

    public boolean isSuccess() {
        return success != null;
    }
}
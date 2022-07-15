package com.tyss.optimize.data.models.dto;

import java.util.Date;

public class ExceptionResponseDTO {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponseDTO(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
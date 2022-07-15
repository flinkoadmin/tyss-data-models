package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDetails {
    private String fullName;
    private String state;
    private String aptSuite;
    private String city;
    private String zip;
    private String address;
    private String country;
    private String netTotal;
    private String tax;
    private String grandTotal;
}

package com.tyss.optimize.data.models.dto.kafka;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FireflinkMessageDto {
    private String Id;
    private String clientId;
    private String type;
    private String reference;
    private Object message;
}

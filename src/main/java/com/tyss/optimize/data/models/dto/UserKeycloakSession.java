package com.tyss.optimize.data.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("clients")
public class UserKeycloakSession {
    private  String id;
    private String username;
    private  String userId;
    private  String  ipAddress;
    private  String start;
    private String lastAccess;
}

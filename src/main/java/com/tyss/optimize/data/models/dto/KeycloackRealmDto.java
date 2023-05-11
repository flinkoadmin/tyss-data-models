package com.tyss.optimize.data.models.dto;

import lombok.Data;

@Data
public class KeycloackRealmDto {

    private String realms;
    private String id;
    private String secret;
    private String loginurl;
    private String grant_type;
    private String refreshType;
}

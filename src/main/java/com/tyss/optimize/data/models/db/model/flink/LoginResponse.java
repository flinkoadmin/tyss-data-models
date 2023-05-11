package com.tyss.optimize.data.models.db.model.flink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String access_token;
    private String refresh_token;
    private String expires_in;
    private String refresh_expires_in;
    private String token_type;

    private String scope;
    private String id;
    private String name;
    private String userName;
    private String licenseId;
    private String privilege;
    private String activationStatus;
    private String jti;
    private String licenseStatus;
    private String defaultLicenseId;
    private String licenseType;
    private String currentLicenseId;
    private String currentPrivilege;
    private String userStatus;

    private String lastSessionData;
}

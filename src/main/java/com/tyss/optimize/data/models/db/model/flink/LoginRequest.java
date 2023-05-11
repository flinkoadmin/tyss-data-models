package com.tyss.optimize.data.models.db.model.flink;

import lombok.Data;

@Data
public class LoginRequest {
    //private String username;
    private String emailId;
    private String password;
    private String currentLicenseId;
    private String source;
    private String refreshToken;
    private String lastSessionData;
    private String realm;
}

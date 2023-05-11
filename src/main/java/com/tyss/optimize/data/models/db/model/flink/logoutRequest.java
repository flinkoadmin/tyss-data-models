package com.tyss.optimize.data.models.db.model.flink;

import lombok.Data;
@Data
public class logoutRequest {
    private String emailId;
    private String currentLicenseId;
    private String refreshToken;
    private String accessToken;

}

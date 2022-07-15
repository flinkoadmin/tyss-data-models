package com.tyss.optimize.data.models.db.model.auth;

import lombok.Data;

@Data
public class AuthToken {

	String access_token ;
	String token_type;
	String refresh_token;
	String expires_in;
	String scope;
	String id;
	String name;
	String userName;
	String licenseId;
	String privilege;
	String activationStatus;
	String jti;
	String licenseStatus;
	String defaultLicenseId;
}

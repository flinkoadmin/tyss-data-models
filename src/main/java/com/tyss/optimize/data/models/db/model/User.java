package com.tyss.optimize.data.models.db.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(value = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity{

	@Transient
	public static final String SEQUENCE_NAME = "USER";

	private String id;
	private String name;
	@NotNull(message = "email is mandatory")
	//@Email(message = "provide valid email")
	@NotBlank(message = "email id must not be blank")
	@Pattern(message = "Please provide valid email",regexp= "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")
	private String emailId;
	private String password;
	private Phone phoneNumbers;
	private String privilege;
//	private List<License> licenses;
	private List<LicenseDetails> licenses;
	private List<Product> products;
	private String defaultLicenseId;
	private String provider;
	private String otp;
	private String activationStatus;
	private String passwordToken;
	private String organisationName;
	private String jobTitle;
	private String jobTitleOthers;
	private String activeToken;
	private String lastSignInOn;
	private String lastSignOutOn;
	@Transient
	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
}

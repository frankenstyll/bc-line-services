package com.ktb.leadandsales.mvc.model;

import lombok.Data;

@Data
public class RegisterModel {

	private String userId;
	private String email;
	private String employeeId;
	
	private String otpNumber;
	private String otpRefNumber;
	
}

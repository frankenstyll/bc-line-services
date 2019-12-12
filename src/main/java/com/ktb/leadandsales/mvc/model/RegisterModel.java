package com.ktb.leadandsales.mvc.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RegisterModel {

	private String userId;
	private String email;
	private String employeeId;
	private String otp;
	private String refNumber;
	private Timestamp created;
	private Timestamp expire;
}

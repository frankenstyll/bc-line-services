package com.ktb.leadandsales.services;

import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;

public interface RegisterServices {

	public ReCaptchaResponse validateRecaptcha(String captchaResponse);
	
	public String RegisterProcess(String empId);
	
	public String validateOtp(String userId, String empId, String otp, String refNumber);
	
	public String resetOtp(String empId, String otp, String refNumber);
}

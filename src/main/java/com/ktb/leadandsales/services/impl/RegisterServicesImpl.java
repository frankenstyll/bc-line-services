package com.ktb.leadandsales.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;
import com.ktb.leadandsales.services.RegisterServices;

@Service
public class RegisterServicesImpl implements RegisterServices{

	private final String urlRegister = "https://webservice-bc.herokuapp.com/webservice-bc/registerRequestOTP";
	private final String urlValidateOtp = "https://webservice-bc.herokuapp.com/webservice-bc/validateOTP";
	private final String urlResetOtp = "https://webservice-bc.herokuapp.com/webservice-bc/resetOTP";
	private final String urlIsRegistered = "https://webservice-bc.herokuapp.com/webservice-bc/isRegistered";
//	private final String urlRegister = "http://localhost:8081/webservice-bc/validateRequestOTP";
	
	private final String reCaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
	private final String secretKey = "6LfIgcMUAAAAALPRdNQyVSfPMv0BUZ4R46jy2yZs";

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String RegisterProcess(String empId) {
	    return restTemplate.getForObject(this.urlRegister + "?employeeId=" + empId , String.class);
	}

	@Override
	public ReCaptchaResponse validateRecaptcha(String captchaResponse) {
		String verifyApiUrl = this.reCaptchaUrl + "?secret=" + this.secretKey + "&response="+captchaResponse;
		return restTemplate.exchange(verifyApiUrl, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
	}

	@Override
	public String validateOtp(String userId ,String empId, String otp, String refNumber) {
		 return restTemplate.getForObject(this.urlValidateOtp + "?userId="+ userId
				 +"&employeeId=" + empId
				 +"&otp=" + otp
				 +"&refNumber="+refNumber
				 , String.class);
	}

	@Override
	public String resetOtp(String empId, String otp, String refNumber) {
		 return restTemplate.getForObject(this.urlResetOtp + "?"+"&employeeId=" + empId
				 +"&otp=" + otp
				 +"&refNumber="+refNumber
				 , String.class);
	}

	@Override
	public String isRegistered(String userId) {
		 return restTemplate.getForObject(this.urlIsRegistered + "?"+"&userId=" + userId
				 , String.class);
	}

}

package com.ktb.leadandsales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;

@Service
public class RegisterServicesImpl implements RegisterServices{

	private final String urlRegister = "https://webservice-bc.herokuapp.com/webservice-bc/validateRequestOTP";
//	private final String urlRegister = "http://localhost:8081/webservice-bc/validateRequestOTP";
	
	private final String reCaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
	private final String secretKey = "6LfIgcMUAAAAALPRdNQyVSfPMv0BUZ4R46jy2yZs";

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String RegisterProcess(String userId) {
	    return restTemplate.getForObject(urlRegister + "?userId=" + userId , String.class);
	}

	@Override
	public ReCaptchaResponse validateRecaptcha(String captchaResponse) {
		String verifyApiUrl = this.reCaptchaUrl + "?secret=" + this.secretKey + "&response="+captchaResponse;
		return restTemplate.exchange(verifyApiUrl, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

	}

}

package com.ktb.leadandsales.services;

import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;

public interface RegisterServices {

	public ReCaptchaResponse validateRecaptcha(String captchaResponse);
	public String RegisterProcess(String userId);
}

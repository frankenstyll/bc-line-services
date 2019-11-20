package com.ktb.leadandsales.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;
import com.ktb.leadandsales.mvc.model.RegisterModel;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	private final String reCaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";
	private final String secretKey = "6LfIgcMUAAAAALPRdNQyVSfPMv0BUZ4R46jy2yZs";
	
	@RequestMapping("/rmregister")
	public String getEmpLineId(HttpServletRequest request,ModelMap model) {
		log.info("[START]getEmpLineId");
		
		log.info("[USER_ID]" + request.getParameter("register"));
		String userId = request.getParameter("register");
		model.addAttribute("register", userId);
		model.addAttribute("message" , null);
		
		log.info("[END]getEmpLineId");
		return "rmRegister";
	}
	
	@PostMapping("/validateRegister")
	public String validateRegister(@ModelAttribute RegisterModel register, 
			@RequestParam(name="g-recaptcha-response") String captchaResponse, ModelMap model) {
		log.info("[START]register");
		
		String returnPage = "rmRegister";
		
		log.info("[USER_ID]" + register.getUserId());
		log.info("[EMP_EMAIL]" + register.getEmail());
		log.info("[EMP_ID]" + register.getEmployeeId());
		log.info("[g-recaptcha-response]" + captchaResponse);
		
		log.info("verify reCaptcha v2" );
		log.info("create url to verify google recaptcha");
		
		String verifyApiUrl = this.reCaptchaUrl + "?secret=" + this.secretKey + "&response="+captchaResponse;
		
		log.info("reCaptchaUrl = " + verifyApiUrl);
		
		log.info("call google recaptcha for verify captcha key");
		
		ReCaptchaResponse reResponse = restTemplate.exchange(verifyApiUrl, HttpMethod.POST
				, null, ReCaptchaResponse.class).getBody();
		
		log.info("verify recaptcha is "+reResponse.isSuccess());
		if(reResponse.isSuccess()) {
			//success
			model.addAttribute("message" , null);
			returnPage = "rmConfirmOTP";
		}else {
			model.addAttribute("message" , "กรุณาระบุ Captcha ใหม่อีกครั้ง");
			returnPage = "rmRegister";
		}
		
		//TODO JOB
		//1.generate otp
		//2.generate ref number
		//3.send otp number
		//4.response result
		
		log.info("[END]register");
		return returnPage;
	}
	
	@PostMapping("/confirmOTP")
	@ResponseBody
	public Map<String,Object> confirmOTP(@ModelAttribute RegisterModel register , ModelMap model) {
		log.info("[START]confirmOTP");
		
		log.info("[USER_ID]" + register.getUserId());
		log.info("[EMP_EMAIL]" + register.getEmail());
		log.info("[EMP_ID]" + register.getEmployeeId());
		
		log.info("[OTP]" + register.getOtpNumber());

		Map<String,Object> resp = new HashMap<String, Object>();
		
		//TODO Job 
		//1.call service check otp
		//2.response result
		try {
			
			resp.put("status", "success");
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		
		log.info("[END]confirmOTP");
		return resp;
	}
	
	@PostMapping("/resetOTP")
	@ResponseBody
	public Map<String,Object> resetOTP(@ModelAttribute RegisterModel register , ModelMap model) {
		log.info("[START]resetOTP");
		
		Map<String,Object> resp = new HashMap<String, Object>();
		
		//TODO Job 
		//1.call service reset OTP
		//2.response result
		try {
			
			resp.put("status", "success");
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		
		log.info("[END]resetOTP");
		return resp;
	}
}

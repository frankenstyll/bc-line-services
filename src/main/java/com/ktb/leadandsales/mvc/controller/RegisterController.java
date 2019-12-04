package com.ktb.leadandsales.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;
import com.ktb.leadandsales.mvc.model.RegisterModel;
import com.ktb.leadandsales.services.RegisterServices;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterServices registerService;
	
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	
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
		
		Gson g = new Gson();
		String returnPage = "rmRegister";
		
		log.info("[USER_ID]" + register.getUserId());
		log.info("[EMP_EMAIL]" + register.getEmail());
		log.info("[EMP_ID]" + register.getEmployeeId());
		log.info("[g-recaptcha-response]" + captchaResponse);
		
		log.info("verify reCaptcha v2" );
		log.info("create url to verify google recaptcha");
		ReCaptchaResponse reResponse = registerService.validateRecaptcha(captchaResponse);
		if(!reResponse.isSuccess()) {
			model.addAttribute("message" , "กรุณาระบุ Captcha ใหม่อีกครั้ง");
			return "rmRegister";
		}
		
		//call register process
		log.info("CALL Register Process");
		String responseRegister = registerService.RegisterProcess(register.getUserId());
		log.info(responseRegister);
		if(null != responseRegister) {
			Map<String,Object> p = g.fromJson(responseRegister, HashMap.class );
		}
		
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

package com.ktb.leadandsales.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktb.leadandsales.mvc.model.RegisterModel;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	//captcha secret key 
	//6LfIgcMUAAAAALPRdNQyVSfPMv0BUZ4R46jy2yZs
	
	@RequestMapping("/bc-line-empid")
	public String getEmpLineId(HttpServletRequest request,ModelMap model) {
		log.info("[START]getEmpLineId");
		
		log.info("[USER_ID]" + request.getParameter("register"));
		String userId = request.getParameter("register");
		model.addAttribute("register", userId);
		
		log.info("[END]getEmpLineId");
		return "bc-line-empid";
	}
	
	@PostMapping("/validateRegister")
	@ResponseBody
	public Map<String,Object> validateRegister(@ModelAttribute RegisterModel register) {
		log.info("[START]register");
		
		log.info("[USER_ID]" + register.getUserId());
		log.info("[EMP_EMAIL]" + register.getEmail());
		log.info("[EMP_ID]" + register.getEmployeeId());
		
		//TODO JOB
		//1.generate otp
		//2.generate ref number
		//3.send otp number
		//4.response result
		
		
		Map<String,Object> resp = new HashMap<String, Object>();
		resp.put("status", "success");
		resp.put("otpNumber","xxxxxxxxxxx"); //TODO wait for call service get otp 
		resp.put("otpRefNumber","YYYYYYYYYY");
		
		log.info("[END]register");
		return resp;
	}
	
	@PostMapping("/confirmOTP")
	@ResponseBody
	public Map<String,Object> confirmOTP(@ModelAttribute RegisterModel register) {
		log.info("[START]confirmOTP");
		
		log.info("[USER_ID]" + register.getUserId());
		log.info("[EMP_EMAIL]" + register.getEmail());
		log.info("[EMP_ID]" + register.getEmployeeId());
		
		log.info("[OTP]" + register.getOtpNumber());
		
		//TODO Job 
		//1.call service check otp
		//2.response result
		
		Map<String,Object> resp = new HashMap<String, Object>();
		resp.put("status", "success");
		
		log.info("[END]confirmOTP");
		return resp;
	}
}

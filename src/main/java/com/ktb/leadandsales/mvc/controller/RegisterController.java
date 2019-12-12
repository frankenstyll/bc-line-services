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
import com.ktb.leadandsales.constant.LineConstant;
import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;
import com.ktb.leadandsales.mvc.model.RegisterModel;
import com.ktb.leadandsales.services.RegisterServices;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterServices registerService;
	
	@Autowired
	private Gson gs;
	
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	
	@RequestMapping("/rmregister")
	public String getEmpLineId(HttpServletRequest request,ModelMap model) {
		log.info("[START]getEmpLineId");
		
		log.info("[USER_ID]" + request.getParameter("register"));
		String userId = request.getParameter("register");
		model.addAttribute("userId", userId);
		model.addAttribute(LineConstant.MESSAGE_TEXT , null);
		
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

		model.addAttribute("userId", register.getUserId());
		model.addAttribute("employeeId", register.getEmployeeId());
		
		log.info("verify reCaptcha v2" );
		log.info("create url to verify google recaptcha");
		ReCaptchaResponse reResponse = registerService.validateRecaptcha(captchaResponse);
		if(!reResponse.isSuccess()) {
			model.addAttribute(LineConstant.MESSAGE_TEXT, "กรุณาระบุ Captcha ใหม่อีกครั้ง");
			return "rmRegister";
		}
		
		//call register process
		log.info("CALL Register Process");
		String responseRegister = registerService.RegisterProcess(register.getEmployeeId());
		log.info(responseRegister);
		if(null != responseRegister) {
			
			Map<String,Object> p = gs.fromJson(responseRegister, HashMap.class );
			
			if ( "0".equals(p.get("status")) ) {
				log.info("send OTP Success");
				model.addAttribute("ref_number" , p.get("ref_number"));
				returnPage = "rmConfirmOTP";
			}else {
				log.info("send OTP error " +  p.get("message"));
				model.addAttribute(LineConstant.MESSAGE_TEXT , p.get("message"));
				return "rmRegister";
			}
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
		log.info("[OTP]" + register.getOtp());

		model.addAttribute("userId", register.getUserId());
		model.addAttribute("employeeId", register.getEmployeeId());
		
		Map<String,Object> resp = new HashMap<String, Object>();
		
		try {
			//TODO 
			//1.call service check otp
			String responseValidate = registerService.validateOtp(
					register.getEmployeeId(), register.getOtp(), register.getRefNumber());
			
			log.info(responseValidate);
			if(null != responseValidate) {
				Map<String,Object> p = gs.fromJson(responseValidate, HashMap.class );
				//2.response result
				if( "".equals(p.get(""))) {
					
				}
				resp.put(LineConstant.STATUS_TEXT, LineConstant.SUCCESS_CODE);
				
			}else {
				throw new Exception("null");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
			resp.put(LineConstant.MESSAGE_TEXT, e.getMessage());
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

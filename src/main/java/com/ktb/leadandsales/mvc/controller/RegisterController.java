package com.ktb.leadandsales.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.mvc.model.ReCaptchaResponse;
import com.ktb.leadandsales.mvc.model.RegisterModel;
import com.ktb.leadandsales.services.RegisterServices;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
    @Autowired
    LineMessageService service;
	
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
		
		log.info("CALL isRegister Process");
		String resp = registerService.isRegistered(userId , "");
		log.info(resp);
		if(null != resp) {
			
			Map<String,Object> p = gs.fromJson(resp, HashMap.class );
			
			if( LineConstant.SUCCESS_CODE.equals(p.get(LineConstant.STATUS_TEXT))) {
				
				if("REGISTERED".equals(p.get(LineConstant.MESSAGE_TEXT))) {
					List<Message> lstMessage = new ArrayList<Message>();
					Message m = new TextMessage("User is already register");
					lstMessage.add(m);
					
					service.handlePushTextContent(userId, lstMessage);
					
					return "alreadyRegister";
				}else {
					
					model.addAttribute("userId", userId);
					model.addAttribute(LineConstant.MESSAGE_TEXT , null);
					
					log.info("[END]getEmpLineId");
					return "rmRegister";
				}
				
			}else {
				model.addAttribute(LineConstant.MESSAGE_TEXT , p.get(LineConstant.MESSAGE_TEXT));
				return "error-page";
			}
		}else {
			
			return "";
		}
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
			
			if( LineConstant.SUCCESS_CODE.equals(p.get(LineConstant.STATUS_TEXT))) {
				log.info("send OTP Success");
				model.addAttribute("ref_number" , p.get("ref_number"));
				model.addAttribute("employee_id" , register.getEmployeeId());
				returnPage = "rmConfirmOTP";
			}else {
				log.info("send OTP error " +  p.get(LineConstant.MESSAGE_TEXT));
				model.addAttribute(LineConstant.MESSAGE_TEXT , p.get(LineConstant.MESSAGE_TEXT));
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
			//1.call service check otp
			log.info("call validate OTP");
			String responseValidate = registerService.validateOtp( register.getUserId(),
					register.getEmployeeId(), register.getOtp(), register.getRefNumber());
			
			log.info(responseValidate);
			if(null != responseValidate) {
				Map<String,Object> p = gs.fromJson(responseValidate, HashMap.class );
				//2.response result
				if( LineConstant.SUCCESS_CODE.equals(p.get(LineConstant.STATUS_TEXT))) {
					resp.put(LineConstant.STATUS_TEXT, LineConstant.SUCCESS_CODE);
				}else {
					resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
					resp.put(LineConstant.MESSAGE_TEXT, p.get(LineConstant.MESSAGE_TEXT));
				}
				
			} else {
				resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
				resp.put(LineConstant.MESSAGE_TEXT, "เกิดข้อผิดพลาด รบกวนติดต่อผู้ดูแลระบบ");
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
		try {
			
			//1.call service reset otp
			String responseReset = registerService.resetOtp(
					register.getEmployeeId(), register.getOtp(), register.getRefNumber());
			
			log.info(responseReset);
			if(null != responseReset) {
				Map<String,Object> p = gs.fromJson(responseReset, HashMap.class );
				//2.response result
				if( LineConstant.SUCCESS_CODE.equals(p.get(LineConstant.STATUS_TEXT))) {
					resp.put(LineConstant.STATUS_TEXT, LineConstant.SUCCESS_CODE);
					resp.put("ref_number" , p.get("ref_number"));
				}else {
					resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
					resp.put(LineConstant.MESSAGE_TEXT, p.get(LineConstant.MESSAGE_TEXT));
				}
				
			} else {
				resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
				resp.put(LineConstant.MESSAGE_TEXT, "เกิดข้อผิดพลาด รบกวนติดต่อผู้ดูแลระบบ");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.put(LineConstant.STATUS_TEXT, LineConstant.FAIL_CODE);
			model.addAttribute(LineConstant.MESSAGE_TEXT, e.getMessage());
		}
		
		log.info("[END]resetOTP");
		return resp;
	}
}

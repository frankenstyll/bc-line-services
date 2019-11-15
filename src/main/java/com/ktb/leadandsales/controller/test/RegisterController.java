package com.ktb.leadandsales.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class RegisterController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping("/bcbot/bc-line-empid")
	public String getEmpLineId(HttpServletRequest request,ModelMap model) {
		log.info("[START]getEmpLineId");
		
		log.info("[USER_ID]" + request.getParameter("register"));
		String userId = request.getParameter("register");
		model.addAttribute("register", userId);
		
		log.info("[END]getEmpLineId");
		return "bc-line-empid";
	}
	
	@PostMapping("/bcbot/register")
	public String register(HttpServletRequest request) {
		log.info("[START]register");
		Gson gson = new Gson();    
		
		log.info("[USER_ID]" + request.getParameter("register"));
		
		log.info("[END]register");
		return "bc-line-empid";
	}
}

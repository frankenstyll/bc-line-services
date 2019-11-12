package com.ktb.leadandsales.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping("/bcbot/bc-line-empid")
	public String getEmpLineId(HttpServletRequest request) {
		log.info("[START]getEmpLineId");
		log.info("[END]getEmpLineId");
		return "bc-line-empid";
	}
	
}

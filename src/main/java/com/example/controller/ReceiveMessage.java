package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveMessage {

	@PostMapping("/bcbot/subscription")
	public void subscription(HttpServletRequest request , HttpServletResponse response) {
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		
	}
	
}

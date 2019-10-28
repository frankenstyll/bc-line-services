package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class ReceiveMessage {

	@GetMapping("/bcbot/subscription")
	public @ResponseBody String subscription(HttpServletRequest request , HttpServletResponse response) {
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		
		Map<String,Object> result = new HashMap<>();
		
		result.put("message", "subscription message");
		return new Gson().toJson(result);
	}
	
}

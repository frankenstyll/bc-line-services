package com.ktb.leadandsales.controller.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;

@RestController
public class ReceiveMessage {

	
	@GetMapping("/bcbot/subscription")
	public @ResponseBody String subscription(
			@RequestParam("replyToken") String replyToken ,
			@RequestParam("userId") String userId
			) {
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		System.out.println("subscription");
		
		Map<String,Object> result = new HashMap<>();
		try {
			result.put("replyToken", replyToken);
			result.put("userId", userId);
			result.put("message", "subscription message");
			
		}catch(Exception ex) {
			result.put("message","error " + ex.getMessage());
		}
		return new Gson().toJson(result);
	}
//	@GetMapping("/bcbot/subscription")
//	public @ResponseBody String subscription(HttpServletRequest request , HttpServletResponse response) {
//		System.out.println("subscription");
//		System.out.println("subscription");
//		System.out.println("subscription");
//		System.out.println("subscription");
//		
//		Map<String,Object> result = new HashMap<>();
//		
//		result.put("message", "subscription message");
//		return new Gson().toJson(result);
//	}
}
 
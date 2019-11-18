package com.ktb.leadandsales.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktb.leadandsales.line.bot.controller.LineBotController;
import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.line.model.MessageBean;

@RestController
public class ReceiveMessage {

	private static final Logger log = LoggerFactory.getLogger(LineBotController.class);
	
	@Autowired
	LineMessageService lineService;
	
	@PostMapping("/bcbot/pushMessage")
	public void pushMessage(@RequestBody MessageBean messageBean) {
		
		log.info("[START]pushMessage");
		
		try {
			log.info("Call push message");
			log.info("USERID : " + messageBean.getUserId());
			log.info(""+messageBean.getMessages());
			lineService.handlePushTextContent(messageBean.getUserId() , messageBean.getMessages());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
	}
	
	@PostMapping("/bcbot/broadcastMessage")
	public void broadcastMessage(@RequestBody MessageBean messageBean) {
		
		log.info("[START]pushMessage");
		
		try {
			log.info("Call push message");
			log.info("USERID : " + messageBean.getUserId());
			log.info(""+messageBean.getMessages());
			lineService.handleBroadCastMessageContent("userid text" , messageBean.getMessages());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
	
}
 
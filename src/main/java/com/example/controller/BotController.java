package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.extern.slf4j.Slf4j;

//@ComponentScan(basePackages = "com.example")

@Slf4j
@LineMessageHandler
public class BotController {

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private MessageSource messageSource;

	/*
	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("[BotController][START]handleTextMessage");
		System.out.println("[BotController]" + e);
        TextMessageContent message = e.getMessage();
        System.out.println("[BotController][END]handleTextMessage");
        return new TextMessage("[BotController]"+message.getText());
	}
	*/
	
	@EventMapping
	public void handleTextMessage(MessageEvent<TextMessageContent> event) {
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
	}
	
	private void handleTextContent(String replyToken, Event event, TextMessageContent content) {				
		
		try{			
			
			String text = content.getText();
			String userId = event.getSource().getUserId();
			List<Message> mes = new ArrayList<Message>();
			
			lineMessagingClient.pushMessage(new PushMessage(userId, mes));
			
		}catch(Exception e){			
			e.printStackTrace();
		}
	}
}

package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.response.BotApiResponse;
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

	
//	@EventMapping
//	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
//		System.out.println("[BotController][START]handleTextMessage");
//		System.out.println("[BotController]" + e);
//        TextMessageContent message = e.getMessage();
//        System.out.println("[BotController][END]handleTextMessage");
//        return new TextMessage("[BotController]"+message.getText());
//	
//	}
	
	@EventMapping
	public void handleTextMessage(MessageEvent<TextMessageContent> event) {
		TextMessageContent message = event.getMessage();
		final LineMessagingClient client = LineMessagingClient
		        .builder("01fd9f585adb28aeafc27432da653d5f")
		        .build();

		
		final TextMessage textMessage = new TextMessage("hello");
		final PushMessage pushMessage = new PushMessage("Ue0f8a270ffea40064588037b51272d28",textMessage);
		
		
		final BotApiResponse botApiResponse;
		try {
		    botApiResponse = client.pushMessage(pushMessage).get();
		    
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return;
		}

		System.out.println(botApiResponse);
	
	}
//	
//	private void handleTextContent(String replyToken, Event event, TextMessageContent content) {				
		
//		final LineMessagingClient client = LineMessagingClient
//		        .builder("<channel access token>")
//		        .build();
//
//		
//		final TextMessage textMessage = new TextMessage("hello");
//		final PushMessage pushMessage = new PushMessage("<to>",textMessage);
//
//		final BotApiResponse botApiResponse;
//		try {
//		    botApiResponse = client.pushMessage(pushMessage).get();
//		    
//		} catch (InterruptedException | ExecutionException e) {
//		    e.printStackTrace();
//		    return;
//		}
//
//		System.out.println(botApiResponse);
//		
//		try{			
//			
//			String text = content.getText();
//			String userId = event.getSource().getUserId();
//			List<Message> mes = new ArrayList<Message>();
//			
//			lineMessagingClient.pushMessage(new PushMessage(userId, mes));
//			
//		}catch(Exception e){			
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
	

}

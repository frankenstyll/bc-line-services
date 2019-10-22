package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@LineMessageHandler
@ComponentScan(basePackages = "com.example")
public class BotController {

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private MessageSource messageSource;

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		
	}
	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("[KtbLineNotiApplication][START]handleTextMessage");
		System.out.println("[KtbLineNotiApplication]" + e);
        TextMessageContent message = e.getMessage();
        System.out.println("[KtbLineNotiApplication][END]handleTextMessage");
        return new TextMessage(message.getText());
	}
}

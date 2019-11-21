package com.ktb.leadandsales.line.bot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.line.message.flex.supplier.WelcomeFlexMessageSupplier;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineBotController {

	private static final Logger log = LoggerFactory.getLogger(LineBotController.class);
	
    @Autowired
    LineMessageService service;

    @Autowired
    LineMessagingClient lineMessagingClient;
    
    @EventMapping
    public void handleFollow(FollowEvent event) {
    	log.info("[START] handleFollow");
    	WelcomeFlexMessageSupplier welcome = new WelcomeFlexMessageSupplier();
    	welcome.setUserId(event.getSource().getUserId());
    	service.handleFlexMessageContent(event.getReplyToken(), welcome.get());
    	log.info("[END] handleFollow");
    }
    
    @EventMapping
	public void handleTextMessage(MessageEvent<TextMessageContent> event) {
    	WelcomeFlexMessageSupplier welcome = new WelcomeFlexMessageSupplier();
    	welcome.setUserId(event.getSource().getUserId());
    	service.handleFlexMessageContent(event.getReplyToken(), welcome.get());
//    	service.handleRichFlexMessageContent(event.getReplyToken(), welcome.get());
    }

    @EventMapping
    public void handleUnfollow(UnfollowEvent event) {
    	log.info("[Unfollow]");
    	//TODO unfollow process
        log.info("User Id : " + event.getSource().getUserId());
    }

    @EventMapping
    public void handleStickerMessage(MessageEvent<StickerMessageContent> event) {
        StickerMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleStickerContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleLocationMessage(MessageEvent<LocationMessageContent> event) {
        LocationMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleLocationContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleImageMessage(MessageEvent<ImageMessageContent> event) {
        ImageMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleImageContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleVideoMessage(MessageEvent<VideoMessageContent> event) {
        VideoMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleVideoContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleAudioMessage(MessageEvent<AudioMessageContent> event) {
        AudioMessageContent message = event.getMessage();
        System.out.println("Sender Id : " + event.getSource().getSenderId());
        System.out.println("User Id : " + event.getSource().getUserId());
        service.handleAudioContent(event.getReplyToken(), event, message);
    }

}

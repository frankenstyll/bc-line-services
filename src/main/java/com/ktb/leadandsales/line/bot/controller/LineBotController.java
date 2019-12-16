package com.ktb.leadandsales.line.bot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ktb.leadandsales.constant.LineConstant;
import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.line.message.flex.supplier.WelcomeFlexMessageSupplier;
import com.ktb.leadandsales.services.RegisterServices;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
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
    
    @Autowired
    RegisterServices registerServices;
    
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
    	
    	String userId = event.getSource().getUserId();
    	log.info("Call isRegistered");
    	String resp = registerServices.isRegistered(userId , "");
    	log.info(resp);
    	if(null != resp) {
    		Map<String,Object> p = new Gson().fromJson(resp, HashMap.class );
			//2.response result
			if( LineConstant.STATUS_TEXT.equals(p.get(LineConstant.SUCCESS_CODE))) {
				if( "REGISTERED".equals(p.get(LineConstant.MESSAGE_TEXT)) ) {
					
					log.info("User is already register");
					List<Message> lstMessage = new ArrayList<Message>();
					Message m = new TextMessage("We have nothing to answer!");
					lstMessage.add(m);
					
					service.handlePushTextContent(userId, lstMessage);
					
				}else {
					WelcomeFlexMessageSupplier welcome = new WelcomeFlexMessageSupplier();
			    	welcome.setUserId(event.getSource().getUserId());
			    	service.handleFlexMessageContent(event.getReplyToken(), welcome.get());
				}
				
			}else {
				WelcomeFlexMessageSupplier welcome = new WelcomeFlexMessageSupplier();
		    	welcome.setUserId(event.getSource().getUserId());
		    	service.handleFlexMessageContent(event.getReplyToken(), welcome.get());
			}
		} 
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

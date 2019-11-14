package com.ktb.leadandsales.line.bot.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.line.message.flex.supplier.WelcomeFlexMessageSupplier;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;

@LineMessageHandler
public class LineBotController {

	private static final Logger log = LoggerFactory.getLogger(LineBotController.class);
	
    @Autowired
    LineMessageService service;

    @Autowired
    LineMessagingClient lineMessagingClient;
    
    @EventMapping
    public Message handleFollow(FollowEvent event) {
    	System.out.println("[handleFollow][START]handleFollow");
        System.out.println("User Id : " + event.getSource().getUserId());
        System.out.println("[handleFollow][END]handleFollow");
        String messageResponse = "UserId : "+event.getSource().getUserId() 
        		+ ", replyToken : " + event.getReplyToken();
        
        return new TextMessage(messageResponse);
    }
    
    @EventMapping
	public void handleTextMessage(MessageEvent<TextMessageContent> e) {
    	
    	 this.reply(e.getReplyToken(), new WelcomeFlexMessageSupplier().get());
    }
    /**for test*/
    private void replyText(@NonNull String replyToken, @NonNull String message) {
        if(replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }

        if(message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "...";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse response = lineMessagingClient.replyMessage(
                    new ReplyMessage(replyToken, messages)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    /**for test*/
    
    
//    
//	@EventMapping
//	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
//		System.out.println("[BotController][START]handleTextMessage");
//		System.out.println("[BotController]" + e);
//        TextMessageContent message = e.getMessage();
//        System.out.println("[BotController][END]handleTextMessage");
//        
//        String messageResponse = "";
//        try {
//        	String userId = e.getSource().getUserId();
//        	String replyToken = e.getReplyToken();
//        	
////        	messageResponse += "UserId : " + userId + ",";
////        	messageResponse += "replyToken : " + replyToken;
//        	
//        	//for test
//        	messageResponse = "กรุณาระบุรหัสพนักงานเพื่อรับการแจ้งเตือนข้อมูลตาม Link ด้านล่าง\n";
//        	messageResponse += "https://glacial-peak-48383.herokuapp.com/bcbot/bc-line-empid";
//        	
//    	}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//        
//        //return new TextMessage("[BotController]"+ messageResponse);
//        return new TextMessage(messageResponse);
//	
//	}

    @EventMapping
    public void handleUnfollow(UnfollowEvent event) {
        System.out.println("User Id : " + event.getSource().getUserId());
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

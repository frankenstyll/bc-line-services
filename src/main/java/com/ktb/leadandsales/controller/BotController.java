package com.ktb.leadandsales.controller;

import java.net.URL;

//@ComponentScan(basePackages = "com.example")

//@LineMessageHandler
public class BotController {
	
//	@Autowired
//	private LineMessagingClient lineMessagingClient;
//
//	@Autowired
//	private MessageSource messageSource;
//
//    @EventMapping
//    public Message handleFollow(FollowEvent event) {
//    	System.out.println("[handleFollow][START]handleFollow");
//        System.out.println("User Id : " + event.getSource().getUserId());
//        System.out.println("[handleFollow][END]handleFollow");
//        String messageResponse = "UserId : "+event.getSource().getUserId() 
//        		+ ", replyToken : " + event.getReplyToken();
//        
//        return new TextMessage(messageResponse);
//    }
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
//        	messageResponse += "UserId : " + userId + ",";
//        	messageResponse += "replyToken : " + replyToken;
//        	
//    	}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//        
//        return new TextMessage("[BotController]"+ messageResponse);
//	
//	}
//	
//	@EventMapping
//	public void handleTextMessage(MessageEvent<TextMessageContent> event) {
//		
//		
//		TextMessageContent message = event.getMessage();
//		final LineMessagingClient client = LineMessagingClient
//		        .builder("01fd9f585adb28aeafc27432da653d5f")
//		        .build();
//
//		
//		final TextMessage textMessage = new TextMessage("hello");
//		final PushMessage pushMessage = new PushMessage("Ue0f8a270ffea40064588037b51272d28",textMessage);
//		
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
	
//	}
	
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

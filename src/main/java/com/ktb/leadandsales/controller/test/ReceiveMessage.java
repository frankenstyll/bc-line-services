package com.ktb.leadandsales.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ktb.leadandsales.line.bot.controller.LineBotController;
import com.ktb.leadandsales.line.bot.service.LineMessageService;
import com.ktb.leadandsales.model.MessageBean;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.response.BotApiResponse;

@RestController
public class ReceiveMessage {

	private static final Logger log = LoggerFactory.getLogger(LineBotController.class);
	
	@Autowired
	LineMessageService lineService;
	
	@PostMapping("/bcbot/receiveMessage")
	public void receiveMessage(@RequestParam("userId") String userId) {
		log.info("[START]subscription");
		log.info("[subscription][userId]" + userId);
		
		final LineMessagingClient client = LineMessagingClient
		        .builder("Qgk56WXeyZ3kQiY+rGfbgwBJfTrgdRxQGu+jwwSNPsRH6cu5UyGSQa7E6Ee33JAglr4ArzpGF6kLYuhcLLvj/DJws9VMTvVXJin7B9Ykde3fdlQQmmxdnIQ64o6UfH+8IX4JRNsD+PF5sLgkrKhYGwdB04t89/1O/w1cDnyilFU=")
		        .build();

		final TextMessage textMessage = new TextMessage("hello");
		final PushMessage pushMessage = new PushMessage(
		        "Ue0f8a270ffea40064588037b51272d28",
		        textMessage);

		final BotApiResponse botApiResponse;
		try {
		    botApiResponse = client.pushMessage(pushMessage).get();
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return;
		}

		System.out.println(botApiResponse);
	}
	
	@PostMapping("/bcbot/pushMessage")
	public void pushMessage(@ModelAttribute MessageBean messageBean) {
		
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

}
 
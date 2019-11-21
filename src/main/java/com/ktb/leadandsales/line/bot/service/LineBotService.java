package com.ktb.leadandsales.line.bot.service;

import com.ktb.leadandsales.constant.LineConstant;
import com.ktb.leadandsales.line.action.LineActionService;
import com.linecorp.bot.model.message.Message;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LineBotService extends LineActionService {

    // send message <= 2000 char
    public void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

	public void replyTextWithRich(@NonNull String replyToken, @NonNull Message messages) {
		actionMessage(replyToken, Collections.singletonList(messages) , true);
	}
	
    public void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        actionMessage(replyToken, messages);
    }

    public void push(@NonNull String userId, @NonNull Message message) {
        push(userId, Collections.singletonList(message));
    }

    public void push(@NonNull String userId, @NonNull List<Message> messages) {
        actionMessage(LineConstant.ACTION_PUSH, userId, messages , false);
    }
    
    public void broadcast(@NonNull String userId, @NonNull List<Message> messages) {
    	actionMessage(LineConstant.ACTION_BROADCAST, userId, messages , false);
    }

}

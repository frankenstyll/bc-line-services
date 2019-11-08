package com.ktb.leadandsales.line.action;

import com.ktb.leadandsales.constant.LineConstant;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public abstract class LineActionService implements ILineActionService{

    @Autowired
    private LineMessagingClient lineMessagingClient;

    protected void checkReceiver(@NonNull String receiver) {
        if (receiver.isEmpty()) {
            throw new IllegalArgumentException("replyToken is not empty");
        }
    }

    protected void actionMessage(@NonNull String to, @NonNull List<Message> messages) {
        actionMessage(LineConstant.ACTION_REPLY, to, messages);
    }

    protected void actionMessage(@NonNull String actionType, @NonNull String to, @NonNull List<Message> messages) {
        checkReceiver(to);
        try {
            BotApiResponse response;
            if (actionType.equalsIgnoreCase(LineConstant.ACTION_PUSH)) {
                response = lineMessagingClient.pushMessage(
                        new PushMessage(to, messages)
                ).get();
            } 
            else if(actionType.equalsIgnoreCase(LineConstant.ACTION_BROADCAST)) {
            	lineMessagingClient.broadcast(new Broadcast(messages, true));
            	
            }
            else {
                response = lineMessagingClient.replyMessage(
                        new ReplyMessage(to, messages)
                ).get();
            }
            
            //System.out.println(response.getDetails());
            
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

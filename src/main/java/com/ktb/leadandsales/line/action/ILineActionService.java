package com.ktb.leadandsales.line.action;

import com.linecorp.bot.model.message.Message;

import java.util.List;

public interface ILineActionService {
    void reply(String replyToken, Message message);
    void reply(String replyToken, List<Message> message);
    void replyTextWithRich(String replyToken, Message message);
    void push(String userId, Message message);
    void push(String userId, List<Message> message);
    void broadcast(String userId, List<Message> message);
}

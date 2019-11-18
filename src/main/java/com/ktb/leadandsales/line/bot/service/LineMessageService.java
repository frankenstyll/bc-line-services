package com.ktb.leadandsales.line.bot.service;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;

@Service
public class LineMessageService {
    @Autowired
    LineBotService service;

    public void handleStickerContent(String replyToken, Event event,
                                     StickerMessageContent stickerMessageContent) {
        List<String> conversationWithPackageId = asList("11537", "11538", "11539");

        if (conversationWithPackageId.contains(stickerMessageContent.getPackageId())) {
            service.reply(replyToken, asList(
                    new StickerMessage("11537", "52002739"),
                    new TextMessage("ผมตอบได้แค่นี้แหละ")
                )
            );
        }
    }

    public void handleLocationContent(String replyToken, Event event,
                                      LocationMessageContent locationMessageContent) {
//        service.reply(replyToken, new TextMessage("ยังไม่รอบรับ location ตอนนี้ แต่เห็นค่านะ " +
//                "latitude = " + locationMessageContent.getLatitude() + " : " +
//                "longitude = " + locationMessageContent.getLongitude()));
    }

    public void handleImageContent(String replyToken, Event event,
                                   ImageMessageContent imageMessageContent) {
        //service.reply(replyToken, new TextMessage("ยังไม่รอบรับ image ตอนนี้"));
    }

    public void handleVideoContent(String replyToken, Event event,
                                   VideoMessageContent videoMessageContent) {
//        service.reply(replyToken, new TextMessage("ยังไม่รอบรับ video ตอนนี้"));
    }

    public void handleAudioContent(String replyToken, Event event,
                                   AudioMessageContent audioMessageContent) {
//        service.reply(replyToken, new TextMessage("ยังไม่รอบรับ audio ตอนนี้"));
    }

    public void handlePushTextContent(String userId , List<Message> messages) {
    	service.push(userId, messages);
    }
    
    public void handleBroadCastMessageContent(String userId, List<Message> messages) {
    	service.broadcast(userId, messages);
    }
    
}

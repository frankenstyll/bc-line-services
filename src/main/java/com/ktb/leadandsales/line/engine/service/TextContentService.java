package com.ktb.leadandsales.line.engine.service;

import com.ktb.leadandsales.line.bot.service.LineBotService;
import com.ktb.leadandsales.line.engine.model.ActionTextModel;
import com.ktb.leadandsales.line.message.flex.supplier.InquirySummaryFlexMessageSupplier;
import com.ktb.leadandsales.line.message.flex.supplier.SprintSummaryFlexMessageSupplier;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class TextContentService {

    @Autowired
    LineBotService service;

    @Autowired
    InquirySummaryFlexMessageSupplier inquirySummaryFlexMessageSupplier;

    @Autowired
    SprintSummaryFlexMessageSupplier sprintSummaryFlexMessageSupplier;

    public void reply(String replyToken, String input) {
        String prefix = "InsureTech";
        if (input.startsWith(prefix)) {
            action(replyToken, input);
        }
    }

    public ActionTextModel analyze(String input) {
        ActionTextModel result = new ActionTextModel();
        String prefix = "InsureTech";
        input = input.substring(prefix.length()).trim();
        if (!input.equalsIgnoreCase("")) {
            System.out.println(input);
            result = mapModel(input);
            System.out.println(result.getAction());
            System.out.println(result.getLabelSprint());
        }
        return result;
    }

    public ActionTextModel mapModel(String input) {
        ActionTextModel result = new ActionTextModel();
        return mapViewSprintToModel(result, input, 1, 3);
    }

    private ActionTextModel mapViewSprintToModel(ActionTextModel actionTextModel, String input, int index, int max) {
        if (index == max) {
            List<String> labelBacklogItemType = null;
            if (input.contains(",")) {
                String[] array = input.split(",");
                labelBacklogItemType = new ArrayList<>();
                for (String data : array) {
                    labelBacklogItemType.add(data);
                }

            } else if (!input.trim().equalsIgnoreCase("")){
                labelBacklogItemType = new ArrayList<>();
                labelBacklogItemType.add(input);
            }

            if (labelBacklogItemType != null)
                actionTextModel.setLabelBacklogItemType(labelBacklogItemType);

        } else if (input.contains(" ")) {
            String value = input.substring(0, input.indexOf(" ")).trim();
            mapCondition(actionTextModel, value, index);
            int running = index + 1;
            mapViewSprintToModel(actionTextModel, input.substring(input.indexOf(" ")).trim(), running, max);
        } else {
            mapCondition(actionTextModel, input, index);
        }
        return actionTextModel;
    }

    private void mapCondition(ActionTextModel actionTextModel, String input, int index) {
        if (index == 1) {
            if (!input.trim().equalsIgnoreCase("")) {
                actionTextModel.setAction(input);
            }
        } else if (index == 2) {
            if (!input.trim().equalsIgnoreCase("")) {
                actionTextModel.setLabelSprint(input);
            }
        }
    }

    //Actiontype  labelSprint labelฺฟแาสนเณะำทType member
    //viewSprint Sprint#7 KIA,FBI
    //viewMember Sprint#7 KIA,FBI พลอย
    //getMember
    //getSprint
    //getType


    private void action(String replyToken, String text) {

//        if (text.equalsIgnoreCase("flex")) {
//            service.reply(replyToken, inquirySummaryFlexMessageSupplier.get());
//        } else if (text.equalsIgnoreCase("text")) {
//            service.reply(replyToken, new TextMessage("ทดสอบอะไรบางอย่าง : " + text));
//        } else if (text.equalsIgnoreCase("sprint")) {
//            service.reply(replyToken, sprintSummaryFlexMessageSupplier.get());
//        } else if (text.equalsIgnoreCase("tinavan push")) {
//            service.reply(replyToken, new TextMessage("ทดสอบ push"));
//            //service.push(event.getSource().getUserId(), sprintSummaryFlexMessageSupplier.get());
//        } else {
            List<String> sayHi = asList("สวัสดี", "ดี", "ดีจ้า", "ไง", "หวัดดี", "สวีดัด", "Hello", "Hi", "hello", "hi");
            try {
                ActionTextModel actionTextModel = analyze(text);
                if (actionTextModel.getAction() != null && actionTextModel.getAction().equalsIgnoreCase("viewSprint")) {
                    sprintSummaryFlexMessageSupplier.preload(actionTextModel);
                    service.reply(replyToken, sprintSummaryFlexMessageSupplier.get());
                } else if (actionTextModel.getAction() != null && sayHi.contains(actionTextModel.getAction())) {
                    service.reply(replyToken, asList(
                            new TextMessage("สวัสดีครับ InsureTech เองครับ ผมถูกสร้างขึ้นมาเพื่อคอยช่วยตอบคำถาม เกี่ยวกับทีม InsureTech ครับ " +
                                    "ตอนนี้คำสั่งพื้นฐานที่ผมพอจะช่วยได้คือดูสรุป Sprint ครับ ดูได้แบบ Point ก่อนนะครับ โดยพิมพ์แบบนี้นะครับ"),
                            new TextMessage("InsureTech viewSprint Sprint#4"),
                            new TextMessage("ถ้าอยากดูแบบทั้งหมดของตอนนี้สามารถทำได้แบบนี้นะครับ"),
                            new TextMessage("InsureTech viewSprint"),
                            new TextMessage("แต่อย่างที่เห็นนะครับ ความสามารถผมยังได้แค่นี้ครับ เพราะผมถูกสร้างแบบ MVP ครับ ยังไงก็ฝากตัวด้วยนะครับ")
                    ));
                } else {
                    service.reply(replyToken, new TextMessage("คำสั่งตอนนี้รองรับแต่ viewSprint นะครับ"));
                }
            } catch (RuntimeException rex) {
                rex.printStackTrace();
                service.reply(replyToken, new TextMessage("ไม่พบข้อมูลที่ต้องการค้นหาครับ"));
            }
//        }

    }

}

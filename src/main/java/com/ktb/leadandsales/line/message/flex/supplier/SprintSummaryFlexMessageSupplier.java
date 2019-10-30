package com.ktb.leadandsales.line.message.flex.supplier;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktb.leadandsales.constant.KiaTrelloConstant;
import com.ktb.leadandsales.line.engine.model.ActionTextModel;
import com.ktb.leadandsales.line.message.flex.component.builder.model.TextBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.implement.TextBuilderService;
import com.ktb.leadandsales.trello.api.model.RequestSprintDashboard;
import com.ktb.leadandsales.trello.api.model.SprintDashboardDto;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

@Service
public class SprintSummaryFlexMessageSupplier implements Supplier<FlexMessage> {

    @Autowired
    TextBuilderService textBuilderService;

    @Autowired
    ObjectMapper objectMapper;

    RequestSprintDashboard requestSprintDashboard;

    @Override
    public FlexMessage get() {
        FlexMessage flexMessage;
        if (this.requestSprintDashboard == null) {
            flexMessage = getDefaultMessage();
        } else {

            String uri = KiaTrelloConstant.KIA_SPRINT_DAHBOARD
                    + "/" + KiaTrelloConstant.SPRINT_MODE_POINT
                    + "/" + KiaTrelloConstant.CHANNEL_LINE;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = null;
            try {
                String json = objectMapper.writeValueAsString(this.requestSprintDashboard);
                request = new HttpEntity<String>(json, headers);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            SprintDashboardDto[] sprintDashboardDtoArray = restTemplate.postForObject(uri, request, SprintDashboardDto[].class);
            List<Bubble> items = new ArrayList<>();
            List<String> bgColors = asList("#FF5733", "#FFEC33", "#33FF5B");
            int index = 0;
            for (SprintDashboardDto sprintDashboardDto : sprintDashboardDtoArray) {
                System.out.println("TaskListName = " + sprintDashboardDto.getTaskListName());
                System.out.println("TaskListSummary = " + sprintDashboardDto.getTaskListSummary());
                System.out.println("TaskListPosition = " + sprintDashboardDto.getTaskListPosition());
                items.add(
                        createBubble(
                                sprintDashboardDto.getTaskListName(),
                                Integer.toString(sprintDashboardDto.getTaskListSummary()),
                                bgColors.get(index)
                                )
                );
                index++;
            }
            final Carousel carousel = Carousel.builder()
                    .contents(items)
                    .build();
            flexMessage = new FlexMessage("Summary Report Sprint", carousel);
        }
        return flexMessage;
    }

    private FlexMessage getDefaultMessage() {
        FlexMessage flexMessage;
        final Bubble item1 = createBubble("Product backlog item", "0", "#FF5733");
        final Bubble item2 = createBubble("Doing", "0", "#FFEC33");
        final Bubble item3 = createBubble("Done", "0", "#33FF5B");

        final Carousel carousel = Carousel.builder()
                .contents(asList(item1, item2, item3))
                .build();
        flexMessage = new FlexMessage("Summary Report Sprint", carousel);
        return flexMessage;
    }

    public void preload(ActionTextModel actionTextModel) {
        this.requestSprintDashboard = new RequestSprintDashboard();
        this.requestSprintDashboard.setTaskGroupId(KiaTrelloConstant.KIA_SPRINT_TASK_GROUP_ID);
        String labelSprint = actionTextModel.getLabelSprint();
        if (labelSprint != null && (!labelSprint.trim().equalsIgnoreCase(""))) {
            this.requestSprintDashboard.setSprintName(labelSprint);
        }

        if (actionTextModel.getLabelBacklogItemType() != null) {
            List<String> labalNames = new ArrayList<>();
            for (String data : actionTextModel.getLabelBacklogItemType()) {
                labalNames.add(data);
            }
            this.requestSprintDashboard.setLabelNames(labalNames);
        }

    }

    private Bubble createBubble(String title, String point, String backgroundColor) {
        final Box headerBox = createHeaderBlock(title);
        final Box bodyBox = createBodyBlock(point, backgroundColor);

        return Bubble.builder()
                .header(headerBox)
                .body(bodyBox)
                .build();
    }

    public Box createHeaderBlock(String title) {
        Text textBuilder = textBuilderService.create(
                loadTextBuilderDto(title, "#000000", FlexAlign.CENTER, FlexFontSize.Md, Text.TextWeight.BOLD)
        );

        return Box.builder()
                .layout(FlexLayout.HORIZONTAL)
                .contents(asList(textBuilder))
                .build();
    }

    public Box createBodyBlock(String point, String backgroundColor) {
        Text textBuilder = textBuilderService.create(
                loadTextBuilderDto(point, "#000000", FlexAlign.CENTER, FlexFontSize.XXXL, Text.TextWeight.BOLD)
        );
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .backgroundColor(backgroundColor)
                .contents(asList(textBuilder))
                .build();
    }

    private TextBuilderDto loadTextBuilderDto(String text, String color,
                                            FlexAlign align, FlexFontSize size, Text.TextWeight weight) {
        TextBuilderDto dto = new TextBuilderDto();
        dto.setText(text);
        dto.setColor(color);
        dto.setAlign(align);
        dto.setSize(size);
        dto.setWeight(weight);
        return dto;
    }

}

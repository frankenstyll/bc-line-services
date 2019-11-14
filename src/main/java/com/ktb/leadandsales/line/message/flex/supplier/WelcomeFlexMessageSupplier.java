package com.ktb.leadandsales.line.message.flex.supplier;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.function.Supplier;

import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Icon;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class WelcomeFlexMessageSupplier implements Supplier<FlexMessage> {
    @Override
    public FlexMessage get() {
        final Box heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .build();
        return new FlexMessage("Welcome to RM Alert", bubbleContainer);
    }

    private Box createHeroBlock() {
        final Text bodyHeaderText = Text.builder()
                .text("RECEIPT")
                .weight(Text.TextWeight.BOLD)
                .color("#1db446")
                .size(FlexFontSize.SM)
                .build();
        final Text bodyTitleHeaderText = Text.builder()
                .text("ข้าวเฮียตุ๋น")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XXL)
                .margin(FlexMarginSize.MD)
                .build();
        final Text bodyTitleHeaderDetail = Text.builder()
                .text("Silom, Bangkok")
                .size(FlexFontSize.XS)
                .color("#aaaaaa")
                .wrap(true)
                .build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(
                        bodyHeaderText,
                        bodyTitleHeaderText,
                        bodyTitleHeaderDetail))
                .build();
    }

    private Box createBodyBlock() {
        final Text title = Text.builder()
                .text("Brown Cafe")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();
        final Box review = createReviewBox();
        final Box info = createInfoBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(asList(title, review, info))
                .build();
    }

    private Box createInfoBox() {
        final Box place = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder()
                            .text("Place")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("Silom, Bangkok")
                            .wrap(true)
                            .color("#666666")
                            .flex(5)
                            .build()
                )).build();
        final Box time = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(asList(
                        Text.builder().text("Time")
                            .color("#aaaaaa")
                            .size(FlexFontSize.SM)
                            .flex(1)
                            .build(),
                        Text.builder()
                            .text("10:00 - 23:00")
                            .wrap(true)
                            .color("#666666")
                            .size(FlexFontSize.SM)
                            .flex(5)
                            .build()
                )).build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(asList(place, time))
                .build();
    }

    private Box createReviewBox() {
        final Icon goldStar = Icon.builder()
                .size(FlexFontSize.SM)
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gold_star.png")
                .build();
        final Icon grayStar = Icon.builder()
                .size(FlexFontSize.SM)
                .url("https://raw.githubusercontent.com/iphayao/line-bot-spring-boot-flex/master/src/main/resources/img/gray_star.png")
                .build();
        final Text point = Text.builder()
                .text("4.0")
                .size(FlexFontSize.SM)
                .color("#999999")
                .margin(FlexMarginSize.MD)
                .flex(0)
                .build();

        return Box.builder()
                .layout(FlexLayout.BASELINE)
                .margin(FlexMarginSize.MD)
                .contents(asList(goldStar, goldStar, goldStar, goldStar, grayStar, point))
                .build();
    }

}

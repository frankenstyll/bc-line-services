package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import lombok.Data;

@Data
public class TextBuilderDto {
    private String text;
    private FlexMarginSize margin;
    private String color;
    private FlexFontSize size;
    private FlexAlign align;
    private Text.TextWeight weight;
    private int flex;

    public TextBuilderDto() {
        this.text = "";
        this.flex = 1;
    }

    public TextBuilderDto(String text, int flex) {
        this.text = text;
        this.flex = flex;
    }

    public TextBuilderDto(String text, String color, FlexFontSize size, int flex) {
        this.text = text;
        this.color = color;
        this.size = size;
        this.flex = flex;
    }

    public TextBuilderDto(String text, FlexMarginSize margin, String color, FlexFontSize size, int flex) {
        this.text = text;
        this.margin = margin;
        this.color = color;
        this.size = size;
        this.flex = flex;
    }
}

package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import lombok.Data;

@Data
public class SeparatorBuilderDto {
    private FlexMarginSize margin;
    private String color;

    public SeparatorBuilderDto() {
    }

    public SeparatorBuilderDto(FlexMarginSize margin, String color) {
        this.margin = margin;
        this.color = color;
    }
}

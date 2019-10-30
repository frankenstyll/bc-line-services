package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import lombok.Data;

@Data
public class SpacerBuilderDto {
    private FlexMarginSize margin;

    public SpacerBuilderDto() {
    }

    public SpacerBuilderDto(FlexMarginSize margin) {
        this.margin = margin;
    }
}

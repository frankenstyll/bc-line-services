package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import lombok.Data;

import java.util.List;

@Data
public class BoxBuilderDto {
    private FlexLayout layout;
    private FlexMarginSize margin;
    private FlexMarginSize spacing;
    private List contents;

    public BoxBuilderDto() {
    }

    public BoxBuilderDto(FlexLayout layout, FlexMarginSize spacing, List contents) {
        this.layout = layout;
        this.spacing = spacing;
        this.contents = contents;
    }

    public BoxBuilderDto(FlexLayout layout, FlexMarginSize margin, FlexMarginSize spacing, List contents) {
        this.layout = layout;
        this.margin = margin;
        this.spacing = spacing;
        this.contents = contents;
    }
}

package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import lombok.Data;

@Data
public class IconBuilderDto {
    private FlexFontSize size;
    private String uri;

    public IconBuilderDto() {

    }

    public IconBuilderDto(String uri) {
        this.uri = uri;
    }

    public IconBuilderDto(FlexFontSize size, String uri) {
        this.size = size;
        this.uri = uri;
    }
}

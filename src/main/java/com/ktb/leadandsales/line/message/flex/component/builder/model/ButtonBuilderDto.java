package com.ktb.leadandsales.line.message.flex.component.builder.model;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.flex.component.Button;
import lombok.Data;

@Data
public class ButtonBuilderDto {
    private Button.ButtonStyle style;
    private Button.ButtonHeight height;
    private Action action;

    public ButtonBuilderDto() {
    }

    public ButtonBuilderDto(Button.ButtonStyle style, Button.ButtonHeight height, Action action) {
        this.style = style;
        this.height = height;
        this.action = action;
    }
}

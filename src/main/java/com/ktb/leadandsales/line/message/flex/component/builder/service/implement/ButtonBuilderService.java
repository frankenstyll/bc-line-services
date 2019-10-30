package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.ButtonBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Button;
import org.springframework.stereotype.Service;

@Service
public class ButtonBuilderService  implements IBuilderService<ButtonBuilderDto, Button> {
    @Override
    public Button create(ButtonBuilderDto builderDto) {
        Button.ButtonBuilder builder = Button.builder();
        if (builderDto.getStyle() != null) builder = builder.style(builderDto.getStyle());
        if (builderDto.getHeight() != null) builder = builder.height(builderDto.getHeight());
        if (builderDto.getAction() != null) builder = builder.action(builderDto.getAction());
        return builder.build();
    }
}

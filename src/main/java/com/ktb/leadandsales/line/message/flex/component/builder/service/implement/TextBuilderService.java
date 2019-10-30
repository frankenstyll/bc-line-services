package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.TextBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Text;
import org.springframework.stereotype.Service;

@Service
public class TextBuilderService implements IBuilderService<TextBuilderDto, Text> {
    @Override
    public Text create(TextBuilderDto builderDto) {

        Text.TextBuilder builder = Text.builder();
        if (builderDto.getText() != null) builder = builder.text(builderDto.getText());
        if (builderDto.getMargin() != null) builder = builder.margin(builderDto.getMargin());
        if (builderDto.getColor() != null) builder = builder.color(builderDto.getColor());
        if (builderDto.getSize() != null) builder = builder.size(builderDto.getSize());
        if (builderDto.getAlign() != null) builder = builder.align(builderDto.getAlign());
        if (builderDto.getWeight() != null) builder = builder.weight(builderDto.getWeight());
        builder = builder.flex(builderDto.getFlex());
        return builder.build();
    }
}

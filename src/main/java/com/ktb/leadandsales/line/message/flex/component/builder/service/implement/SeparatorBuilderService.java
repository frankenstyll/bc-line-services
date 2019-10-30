package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.SeparatorBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Separator;
import org.springframework.stereotype.Service;

@Service
public class SeparatorBuilderService implements IBuilderService<SeparatorBuilderDto, Separator> {
    @Override
    public Separator create(SeparatorBuilderDto builderDto) {
        Separator.SeparatorBuilder builder = Separator.builder();
        if (builderDto.getMargin() != null) builder = builder.margin(builderDto.getMargin());
        if (builderDto.getColor() != null) builder = builder.color(builderDto.getColor());
        return builder.build();
    }
}

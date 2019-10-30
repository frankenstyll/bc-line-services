package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.SpacerBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Spacer;
import org.springframework.stereotype.Service;

@Service
public class SpacerBuilderService implements IBuilderService<SpacerBuilderDto, Spacer> {
    @Override
    public Spacer create(SpacerBuilderDto builderDto) {
        Spacer.SpacerBuilder builder = Spacer.builder();
        if (builderDto.getMargin() != null) builder.size(builderDto.getMargin());
        return builder.build();
    }
}

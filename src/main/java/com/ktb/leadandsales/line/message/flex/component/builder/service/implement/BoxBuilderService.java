package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.BoxBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Box;
import org.springframework.stereotype.Service;

@Service
public class BoxBuilderService implements IBuilderService<BoxBuilderDto, Box> {
    @Override
    public Box create(BoxBuilderDto builderDto) {
        Box.BoxBuilder builder = Box.builder();
        if (builderDto.getLayout() != null) builder = builder.layout(builderDto.getLayout());
        if (builderDto.getMargin() != null) builder = builder.spacing(builderDto.getMargin());
        if (builderDto.getSpacing() != null) builder = builder.spacing(builderDto.getSpacing());
        if (builderDto.getContents() != null) builder = builder.contents(builderDto.getContents());
        return builder.build();
    }
}

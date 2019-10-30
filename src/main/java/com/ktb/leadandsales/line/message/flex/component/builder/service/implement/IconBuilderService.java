package com.ktb.leadandsales.line.message.flex.component.builder.service.implement;

import com.ktb.leadandsales.line.message.flex.component.builder.model.IconBuilderDto;
import com.ktb.leadandsales.line.message.flex.component.builder.service.IBuilderService;
import com.linecorp.bot.model.message.flex.component.Icon;
import org.springframework.stereotype.Service;

@Service
public class IconBuilderService implements IBuilderService<IconBuilderDto, Icon> {
    public Icon create(IconBuilderDto builderDto) {
        Icon.IconBuilder builder = Icon.builder();
        if (builderDto.getSize() != null) builder = builder.size(builderDto.getSize());
        if (builderDto.getUri() != null) builder = builder.url(builderDto.getUri());
        return builder.build();
    }
}

package com.ktb.leadandsales.line.message.flex.component.builder.service;

import org.springframework.stereotype.Service;

@Service
public interface IBuilderService<T, K> {
    K create(T t);
}

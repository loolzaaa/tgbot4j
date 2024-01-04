package ru.loolzaaa.tgbot4j.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BotConfigurer<T> implements ComponentConfigurer<T> {
    protected final String botName;
    protected final String botToken;
}

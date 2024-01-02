package ru.loolzaaa.tgbot4j.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BotConfigurer<T> {

    protected final String botName;
    protected final String botToken;

    protected T botComponent;

    public void setBotComponent(T botComponent) {
        this.botComponent = botComponent;
    }

    public abstract T configure();
}

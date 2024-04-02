package ru.loolzaaa.tgbot4j.config;

import lombok.RequiredArgsConstructor;

/**
 * Abstract implementation of component configurer.
 *
 * Saved bot token and name for descendants.
 *
 * @param <T> type of configuration result
 */
@RequiredArgsConstructor
public abstract class BotConfigurer<T> implements ComponentConfigurer<T> {
    protected final String botName;
    protected final String botToken;
}

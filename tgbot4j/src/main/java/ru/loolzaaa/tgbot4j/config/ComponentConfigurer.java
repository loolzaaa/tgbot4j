package ru.loolzaaa.tgbot4j.config;

/**
 * Interface for implementation of Telegram bot
 * component configurers.
 *
 * @param <T> type of configuration result
 */
@FunctionalInterface
public interface ComponentConfigurer<T> {
    /**
     * Start configure process with some result.
     *
     * @return type of configuration result
     */
    T configure();
}

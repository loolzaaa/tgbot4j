package ru.loolzaaa.tgbot4j.core.bot.sender;

import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

import java.util.concurrent.CompletableFuture;

/**
 * This interface defines communication
 * with the API server.
 * <p>
 * Any API method must be sent through
 * an implementation of this interface.
 */

public interface MethodSender {
    /**
     * Method for making a synchronous request
     * to the API server.
     *
     * @param method sending API method
     * @param <T>    API response type
     * @param <M>    API method type
     * @return deserialized response API response
     */
    <T, M extends TelegramMethod<T>> T send(M method);

    /**
     * Method for making an asynchronous request
     * to the API server.
     *
     * @param method sending API method
     * @param <T>    API response type
     * @param <M>    API method type
     * @return deserialized response API response
     */
    default <T, M extends TelegramMethod<T>> CompletableFuture<T> sendAsync(M method) {
        throw new RuntimeException("Asynchronous method dispatch is not implemented");
    }
}

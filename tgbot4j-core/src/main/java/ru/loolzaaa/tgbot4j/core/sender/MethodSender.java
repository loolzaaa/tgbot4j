package ru.loolzaaa.tgbot4j.core.sender;

import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

import java.util.concurrent.CompletableFuture;

public interface MethodSender {
    <T, M extends TelegramMethod<T>> T send(M method);

    default <T, M extends TelegramMethod<T>> CompletableFuture<T> sendAsync(M method) {
        throw new RuntimeException("Asynchronous method dispatch is not implemented");
    }
}

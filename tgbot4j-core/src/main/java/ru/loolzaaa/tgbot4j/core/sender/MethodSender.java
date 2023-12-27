package ru.loolzaaa.tgbot4j.core.sender;

import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;

public interface MethodSender {
    <T, M extends TelegramMethod<T>> T send(M method);
}

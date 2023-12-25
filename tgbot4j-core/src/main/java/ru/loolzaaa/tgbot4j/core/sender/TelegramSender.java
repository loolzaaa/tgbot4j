package ru.loolzaaa.tgbot4j.core.sender;

import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;

public interface TelegramSender {
    <T, M extends JsonResponseDeserializer<T>> T send(M method);
}

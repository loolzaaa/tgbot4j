package ru.loolzaaa.tgbot4j.core.api;

import java.util.Map;

public interface TelegramMultipartMethod<T> extends TelegramMethod<T> {
    Map<String, byte[]> getParts();
}

package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.util.List;

public interface TelegramMultipartMethod<T> extends TelegramMethod<T> {
    List<MultipartBodyPart> getBodyParts(ObjectMapper mapper) throws IOException;
}

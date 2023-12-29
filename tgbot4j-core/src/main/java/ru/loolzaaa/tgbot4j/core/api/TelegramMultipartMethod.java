package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public interface TelegramMultipartMethod<T> extends TelegramMethod<T> {

    void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException;

    default List<MultipartBodyPart> getBodyParts(ObjectMapper mapper) throws IOException {
        try {
            List<MultipartBodyPart> parts = new ArrayList<>();
            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(JsonProperty.class) || field.get(this) == null) {
                    continue;
                }
                final String partName = field.getAnnotation(JsonProperty.class).value();
                if (field.isAnnotationPresent(MultipartType.class)) {
                    MultipartType.Type type = field.getAnnotation(MultipartType.class).value();
                    switch (type) {
                        case JSON -> parts.add(new MultipartBodyPart(partName, mapper.writeValueAsBytes(field.get(this)), false));
                        case BINARY -> addBinaryBodyPart(parts, field, partName);
                        default -> parts.add(new MultipartBodyPart(partName, field.get(this).toString().getBytes(StandardCharsets.UTF_8), false));
                    }
                } else {
                    parts.add(new MultipartBodyPart(partName, field.get(this).toString().getBytes(StandardCharsets.UTF_8), false));
                }
            }
            return parts;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default void addInputFileBodyPart(List<MultipartBodyPart> parts, InputFile inputFile, String partName) throws IOException {
        parts.add(new MultipartBodyPart(partName, inputFile.getAttachName().getBytes(StandardCharsets.UTF_8), false));
        if (inputFile.getFile() != null) {
            parts.add(new MultipartBodyPart(inputFile.getInputName(), Files.readAllBytes(inputFile.getFile().toPath()), true));
            return;
        }
        if (inputFile.getInputStream() != null) {
            parts.add(new MultipartBodyPart(inputFile.getInputName(), inputFile.getInputStream().readAllBytes(), true));
        }
    }
}

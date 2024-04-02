package ru.loolzaaa.tgbot4j.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * A common interface for those API methods
 * that are requested with a multipart content-type.
 * <p>
 * Each returned response deserialized by {@link TelegramMethod}.
 *
 * @param <T> API response type
 * @see MultipartType
 */

public interface TelegramMultipartMethod<T> extends TelegramMethod<T> {
    /**
     * Adding a binary {@link MultipartBodyPart} to a multipart request
     * based on API method field.
     *
     * @param parts     list already added request body parts
     * @param partField API method field that must
     *                  be added in multipart request
     * @param partName  name of the request part
     * @throws IOException if file I/O error
     */
    void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException;

    /**
     * Creating list of {@link MultipartBodyPart} receiving by
     * {@link MethodSender} implementation.
     * <p>
     * This implementation iterates over all fields
     * of the serializable API method that have
     * the {@link JsonProperty} annotation.
     * <p>
     * The type of body part is determined by
     * the {@link MultipartType} annotation (or it is absent
     * if the data must be serialized as text).
     *
     * @param mapper json mapper for json string
     *               body part serialization
     * @return list of all body parts for multipart request
     * @throws IOException if file I/O error
     *                     or json serialization error
     * @see MultipartBodyPart
     */
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

    /**
     * Default implementation for adding {@link InputFile}
     * {@link MultipartBodyPart} type.
     * <p>
     * Each {@link InputFile} body part must contain attach name
     * and read bytes from file or input stream.
     * <p>
     * If {@link InputFile} contains both file and input stream,
     * file has precedence.
     *
     * @param parts     list already added request body parts
     * @param inputFile input file to be added as body part
     * @param partName  name of the request part
     * @throws IOException if file I/O error
     */
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

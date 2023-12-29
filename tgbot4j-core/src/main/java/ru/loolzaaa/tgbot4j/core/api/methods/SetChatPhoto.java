package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.MultipartType;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.*;

/**
 * Use this method to set a new profile photo for the chat.
 * Photos can't be changed for private chats.
 * The bot must be an administrator in the chat
 * for this to work and must have the appropriate
 * administrator rights. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetChatPhoto implements TelegramMultipartMethod<Boolean> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * New chat photo, uploaded using multipart/form-data
     */
    @MultipartType(BINARY)
    @JsonProperty("photo")
    private InputFile photo;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (photo == null || (photo.getFile() == null && photo.getInputStream() == null)) {
            throw new ApiValidationException("Photo parameter must be uploaded with multipart/form-data", this);
        }
        photo.validate();
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("photo")) {
            addInputFileBodyPart(parts, photo, partName);
        }
    }
}

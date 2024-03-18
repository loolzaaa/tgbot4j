package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.MultipartType;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.api.types.File;
import ru.loolzaaa.tgbot4j.core.api.types.InputFile;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.BINARY;

/**
 * Use this method to upload a file with a sticker
 * for later use in the {@link CreateNewStickerSet}
 * and {@link AddStickerToSet} methods (the file
 * can be used multiple times).
 * Returns the uploaded {@link File} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadStickerFile implements TelegramMultipartMethod<File> {
    /**
     * User identifier of sticker file owner
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * A file with the sticker in .WEBP, .PNG, .TGS, or .WEBM format.
     * See <a href="https://core.telegram.org/stickers">https://core.telegram.org/stickers</a> for technical requirements.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>
     */
    @Required
    @MultipartType(BINARY)
    @JsonProperty("sticker")
    private InputFile sticker;

    /**
     * Format of the sticker, must be one of “static”,
     * “animated”, “video”
     */
    @Required
    @JsonProperty("sticker_format")
    private String stickerFormat;

    @Override
    public File determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, File.class);
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("sticker")) {
            addInputFileBodyPart(parts, sticker, partName);
        }
    }
}

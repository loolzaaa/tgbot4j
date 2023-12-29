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
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static ru.loolzaaa.tgbot4j.core.api.MultipartType.Type.BINARY;

/**
 * Use this method to set the thumbnail of a regular
 * or mask sticker set. The format of the thumbnail file
 * must match the format of the stickers in the set.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStickerSetThumbnail implements TelegramMultipartMethod<Boolean> {
    /**
     * Sticker set name
     */
    @JsonProperty("name")
    private String name;

    /**
     * User identifier of the sticker set owner
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * A <b>.WEBP</b> or <b>.PNG</b> image with the thumbnail,
     * must be up to 128 kilobytes in size and have a width
     * and height of exactly 100px, or a <b>.TGS</b> animation
     * with a thumbnail up to 32 kilobytes in size
     * (see <a href="https://core.telegram.org/stickers#animated-sticker-requirements">https://core.telegram.org/stickers#animated-sticker-requirements</a>
     * for animated sticker technical requirements),
     * or a <b>WEBM</b> video with the thumbnail up to 32 kilobytes in size;
     * see <a href="https://core.telegram.org/stickers#video-sticker-requirements">https://core.telegram.org/stickers#video-sticker-requirements</a>
     * for video sticker technical requirements.
     * Pass a file_id as a String to send a file that already exists
     * on the Telegram servers, pass an HTTP URL as a String
     * for Telegram to get a file from the Internet,
     * or upload a new one using multipart/form-data.
     * <a href="https://core.telegram.org/bots/api#sending-files">More information on Sending Files »</a>.
     * Animated and video sticker set thumbnails
     * can't be uploaded via HTTP URL. If omitted, then the thumbnail
     * is dropped and the first sticker is used as the thumbnail.
     */
    @MultipartType(BINARY)
    @JsonProperty("thumbnail")
    private InputFile thumbnail;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void addBinaryBodyPart(List<MultipartBodyPart> parts, Field partField, String partName) throws IOException {
        if (partField.getName().equals("thumbnail")) {
            addInputFileBodyPart(parts, thumbnail, partName);
        }
    }
}

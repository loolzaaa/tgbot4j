package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputSticker;

import java.util.List;

/**
 * Use this method to create a new sticker set owned by a user.
 * The bot will be able to edit the sticker set thus created.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewStickerSet implements TelegramMethod<Boolean> {
    /**
     * User identifier of created sticker set owner
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Short name of sticker set, to be used in {@code t.me/addstickers/} URLs (e.g., animals).
     * Can contain only English letters, digits and underscores.
     * Must begin with a letter, can't contain consecutive underscores
     * and must end in {@code "_by_<bot_username>"}. {@code <bot_username>}
     *     is case insensitive. 1-64 characters.
     */
    @Required
    @JsonProperty("name")
    private String name;

    /**
     * Sticker set title, 1-64 characters
     */
    @Required
    @JsonProperty("title")
    private String title;

    /**
     * A JSON-serialized list of 1-50 initial stickers
     * to be added to the sticker set
     */
    @Required
    @JsonProperty("stickers")
    private List<InputSticker> stickers;

    /**
     * Format of stickers in the set, must be one of “static”,
     * “animated”, “video”
     */
    @Required
    @JsonProperty("sticker_format")
    private String stickerFormat;

    /**
     * Type of stickers in the set, pass “regular”, “mask”,
     * or “custom_emoji”. By default, a regular sticker set is created.
     */
    @JsonProperty("sticker_type")
    private String stickerType;

    /**
     * Pass True if stickers in the sticker set must be repainted
     * to the color of text when used in messages, the accent color
     * if used as emoji status, white on chat photos,
     * or another appropriate color based on context;
     * for custom emoji sticker sets only
     */
    @JsonProperty("needs_repainting")
    private Boolean needsRepainting;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

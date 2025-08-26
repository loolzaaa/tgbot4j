package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Sends a gift to the given user or channel chat.
 * The gift can't be converted to Telegram Stars
 * by the receiver. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendGift implements TelegramMethod<Boolean> {
    /**
     * Required if chat_id is not specified.
     * Unique identifier of the target user who will receive the gift.
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Required if user_id is not specified. Unique identifier
     * for the chat or username of the channel
     * (in the format {@code @channelusername})
     * that will receive the gift.
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifier of the gift
     */
    @Required
    @JsonProperty("gift_id")
    private String giftId;

    /**
     * Pass True to pay for the gift upgrade from the bot's balance,
     * thereby making the upgrade free for the receiver
     */
    @JsonProperty("pay_for_upgrade")
    private Boolean payForUpgrade;

    /**
     * Text that will be shown along with the gift; 0-128 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * Mode for parsing entities in the text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * Entities other than “bold”, “italic”, “underline”,
     * “strikethrough”, “spoiler”, and “custom_emoji” are ignored.
     */
    @JsonProperty("text_parse_mode")
    private String textParseMode;

    /**
     * A JSON-serialized list of special entities that appear in the gift text.
     * It can be specified instead of text_parse_mode.
     * Entities other than “bold”, “italic”, “underline”, “strikethrough”,
     * “spoiler”, and “custom_emoji” are ignored.
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (giftId == null) {
            throw new ApiValidationException("Gift Id parameter can't be null", this);
        }
    }
}

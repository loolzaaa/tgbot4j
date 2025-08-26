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
 * Gifts a Telegram Premium subscription to the given user. Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftPremiumSubscription implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the target user
     * who will receive a Telegram Premium subscription
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Number of months the Telegram Premium subscription
     * will be active for the user; must be one of 3, 6, or 12
     */
    @Required
    @JsonProperty("month_count")
    private Integer monthCount;

    /**
     * Number of Telegram Stars to pay for the Telegram Premium subscription;
     * must be 1000 for 3 months, 1500 for 6 months, and 2500 for 12 months
     */
    @Required
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * Text that will be shown along with the service message
     * about the subscription; 0-128 characters
     */
    @JsonProperty("text")
    private String text;

    /**
     * Mode for parsing entities in the text.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * Entities other than “bold”, “italic”, “underline”, “strikethrough”,
     * “spoiler”, and “custom_emoji” are ignored.
     */
    @JsonProperty("text_parse_mode")
    private String textParseMode;

    /**
     * A JSON-serialized list of special entities that appear in the gift text.
     * It can be specified instead of text_parse_mode.
     * Entities other than “bold”, “italic”, “underline”,
     * “strikethrough”, “spoiler”, and “custom_emoji” are ignored.
     */
    @JsonProperty("text_entities")
    private List<MessageEntity> textEntities;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (monthCount == null) {
            throw new ApiValidationException("Month Count parameter can't be null", this);
        }
        if (starCount == null) {
            throw new ApiValidationException("Star Count parameter can't be null", this);
        }
        if (monthCount != 3 && monthCount != 6 && monthCount != 12) {
            throw new ApiValidationException("Month count parameter must be 3, 6 or 12", this);
        }
        if ((monthCount == 3 && starCount != 1000) || (monthCount == 6 && starCount != 1500) || (monthCount == 12 && starCount != 2500)) {
            throw new ApiValidationException("Star Count parameter must be 1000 for 3 months, 1500 for 6 months and 2500 for 12 months", this);
        }
        if (text != null && text.length() > 128) {
            throw new ApiValidationException("Text parameter must not exceed 128", this);
        }
    }
}

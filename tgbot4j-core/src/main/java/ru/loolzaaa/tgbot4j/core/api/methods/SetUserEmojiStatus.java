package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Changes the emoji status for a given user that previously
 * allowed the bot to manage their emoji status
 * via the Mini App method <a href="https://core.telegram.org/bots/webapps#initializing-mini-apps">requestEmojiStatusAccess</a>.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetUserEmojiStatus implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Custom emoji identifier of the emoji status to set.
     * Pass an empty string to remove the status.
     */
    @JsonProperty("emoji_status_custom_emoji_id")
    private String emojiStatusCustomEmojiId;

    /**
     * Expiration date of the emoji status, if any
     */
    @JsonProperty("emoji_status_expiration_date")
    private Integer emojiStatusExpirationDate;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
    }
}

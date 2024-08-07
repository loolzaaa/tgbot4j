package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Upon receiving a message with this object,
 * Telegram clients will display a reply interface
 * to the user (act as if the user has selected the bot's message
 * and tapped 'Reply'). This can be extremely useful
 * if you want to create user-friendly step-by-step interfaces
 * without having to sacrifice <a href="https://core.telegram.org/bots/features#privacy-mode">privacy mode</a>.
 * <p>
 * Not supported in channels and for messages sent
 * on behalf of a Telegram Business account.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForceReply implements ReplyMarkup {
    /**
     * Shows reply interface to the user, as if they manually
     * selected the bot's message and tapped 'Reply'
     */
    @JsonProperty("force_reply")
    private Boolean forceReply;

    /**
     * Optional. The placeholder to be shown in the input field
     * when the reply is active; 1-64 characters
     */
    @JsonProperty("input_field_placeholder")
    private String inputFieldPlaceholder;

    /**
     * Optional. Use this parameter if you want to force reply
     * from specific users only. Targets: 1) users that are @mentioned in the text
     * of the Message object; 2) if the bot's message is a reply (has reply_to_message_id),
     * sender of the original message.
     */
    @JsonProperty("selective")
    private Boolean selective;

    @Override
    public void validate() {
        if (forceReply == null) {
            throw new ApiValidationException("Force reply parameter must not be null", this);
        }
        if (inputFieldPlaceholder != null && (inputFieldPlaceholder.isEmpty() || inputFieldPlaceholder.length() > 64)) {
            throw new ApiValidationException("Input field placeholder parameter must be in 1..64 range", this);
        }
    }
}

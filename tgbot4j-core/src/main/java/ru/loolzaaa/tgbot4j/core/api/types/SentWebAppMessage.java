package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes an inline message sent
 * by a <a href="https://core.telegram.org/bots/webapps">Web App</a> on behalf of a user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SentWebAppMessage {
    /**
     * Optional. Identifier of the sent inline message.
     * Available only if there is an {@link ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup} attached to the message.
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
}

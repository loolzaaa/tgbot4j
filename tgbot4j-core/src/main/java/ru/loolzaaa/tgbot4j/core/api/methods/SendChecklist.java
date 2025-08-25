package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InlineKeyboardMarkup;
import ru.loolzaaa.tgbot4j.core.api.types.InputChecklist;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.ReplyParameters;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to send a checklist on behalf
 * of a connected business account.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendChecklist implements TelegramMethod<Message> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be sent
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target chat
     */
    @Required
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * A JSON-serialized object for the checklist to send
     */
    @Required
    @JsonProperty("checklist")
    private InputChecklist checklist;

    /**
     * Sends the message silently. Users will receive
     * a notification with no sound.
     */
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * Protects the contents of the sent message
     * from forwarding and saving
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    /**
     * Unique identifier of the message effect
     * to be added to the message
     */
    @JsonProperty("message_effect_id")
    private String messageEffectId;

    /**
     * A JSON-serialized object for description
     * of the message to reply to
     */
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * A JSON-serialized object for an inline keyboard
     */
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    @Override
    public Message determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Message.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (chatId == null) {
            throw new ApiValidationException("Chat Id parameter can't be null", this);
        }
        if (checklist == null) {
            throw new ApiValidationException("Checklist parameter can't be null", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}

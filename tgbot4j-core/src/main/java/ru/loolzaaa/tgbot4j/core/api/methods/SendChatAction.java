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
 * Use this method when you need to tell the user
 * that something is happening on the bot's side.
 * The status is set for 5 seconds or less
 * (when a message arrives from your bot,
 * Telegram clients clear its typing status).
 * Returns True on success.
 *
 * Example: The ImageBot needs some time to process a request
 * and upload the image. Instead of sending a text message
 * along the lines of “Retrieving image, please wait…”,
 * the bot may use {@link SendChatAction} with action = upload_photo.
 * The user will see a “sending photo” status for the bot.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendChatAction implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target chat
     * or username of the target channel
     * (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier for the target message
     * thread (topic) of the forum;
     * for forum supergroups only
     */
    @JsonProperty("message_thread_id")
    private Integer messageThreadId;

    /**
     * Type of action to broadcast. Choose one, depending on
     * what the user is about to receive: typing for {@link SendMessage},
     * upload_photo for {@link SendPhoto}, record_video or upload_video for {@link SendVideo},
     * record_voice or upload_voice for {@link SendVoice},
     * upload_document for {@link SendDocument}, choose_sticker for {@link SendSticker},
     * find_location for {@link SendLocation}, record_video_note
     * or upload_video_note for {@link SendVideoNote}.
     */
    @Required
    @JsonProperty("action")
    private String action;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (action == null || action.isEmpty()) {
            throw new ApiValidationException("Action parameter can't be null or empty", this);
        }
    }
}

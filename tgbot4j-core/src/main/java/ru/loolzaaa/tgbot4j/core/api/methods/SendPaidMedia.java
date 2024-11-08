package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.*;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Use this method to send paid media.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPaidMedia implements TelegramMethod<Message> {
    /**
     * Unique identifier of the business connection
     * on behalf of which the message will be sent
     */
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier for the target chat
     * or username of the target channel
     * (in the format {@code @channelusername}).
     * If the chat is a channel, all Telegram Star proceeds
     * from this media will be credited to the chat's balance.
     * Otherwise, they will be credited to the bot's balance.
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * The number of Telegram Stars that must be paid
     * to buy access to the media; 1-2500
     */
    @Required
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * A JSON-serialized array describing the media to be sent;
     * up to 10 items
     */
    @Required
    @JsonProperty("media")
    private List<InputPaidMedia> media;

    /**
     * Bot-defined paid media payload, 0-128 bytes.
     * This will not be displayed to the user,
     * use it for your internal processes.
     */
    @JsonProperty("payload")
    private String payload;

    /**
     * Media caption, 0-1024 characters after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the media caption.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("parse_mode")
    private String parseMode;

    /**
     * A JSON-serialized list of special entities
     * that appear in the caption, which
     * can be specified instead of parse_mode
     */
    @JsonProperty("caption_entities")
    private List<MessageEntity> captionEntities;

    /**
     * Pass True, if the caption must be shown above the message media
     */
    @JsonProperty("show_caption_above_media")
    private Boolean showCaptionAboveMedia;

    /**
     * Sends the message <a href="https://telegram.org/blog/channels-2-0#silent-messages">silently</a>.
     * Users will receive a notification with no sound.
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
     * Pass True to allow up to 1000 messages per second,
     * ignoring <a href="https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once">
     * broadcasting limits</a> for a fee of 0.1 Telegram Stars per message.
     * The relevant Stars will be withdrawn from the bot's balance
     */
    @JsonProperty("allow_paid_broadcast")
    private Boolean allowPaidBroadcast;

    /**
     * Description of the message to reply to
     */
    @JsonProperty("reply_parameters")
    private ReplyParameters replyParameters;

    /**
     * Additional interface options.
     * A JSON-serialized object for an <a href="https://core.telegram.org/bots/features#inline-keyboards">inline keyboard</a>,
     * <a href="https://core.telegram.org/bots/features#keyboards">custom reply keyboard</a>, instructions to remove a reply keyboard
     * or to force a reply from the user.
     */
    @JsonProperty("reply_markup")
    private ReplyMarkup replyMarkup;

    @Override
    public Message determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Message.class);
    }

    @Override
    public void validate() {
        if (chatId == null || chatId.isEmpty()) {
            throw new ApiValidationException("Chat ID parameter can't be null or empty", this);
        }
        if (starCount == null) {
            throw new ApiValidationException("Star count parameter can't be null", this);
        }
        if (media == null) {
            throw new ApiValidationException("Photo parameter can't be null", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}

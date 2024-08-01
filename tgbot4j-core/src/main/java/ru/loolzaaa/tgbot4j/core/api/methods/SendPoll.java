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
 * Use this method to send a native poll.
 * On success, the sent {@link Message} is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendPoll implements TelegramMethod<Message> {
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
     * Poll question, 1-300 characters
     */
    @Required
    @JsonProperty("question")
    private String question;

    /**
     * Mode for parsing entities in the question.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     * Currently, only custom emoji entities are allowed
     */
    @JsonProperty("question_parse_mode")
    private String questionParseMode;

    /**
     *  A JSON-serialized list of special entities that appear
     *  in the poll question. It can be specified instead
     *  of question_parse_mode
     */
    @JsonProperty("question_entities")
    private List<MessageEntity> questionEntities;

    /**
     * A JSON-serialized list of 2-10 answer options
     */
    @Required
    @JsonProperty("options")
    private List<InputPollOption> options;

    /**
     * True, if the poll needs to be anonymous, defaults to True
     */
    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;

    /**
     * Poll type, “quiz” or “regular”, defaults to “regular”
     */
    @JsonProperty("type")
    private String type;

    /**
     * True, if the poll allows multiple answers,
     * ignored for polls in quiz mode, defaults to False
     */
    @JsonProperty("allows_multiple_answers")
    private Boolean allowsMultipleAnswers;

    /**
     * 0-based identifier of the correct answer option,
     * required for polls in quiz mode
     */
    @JsonProperty("correct_option_id")
    private Integer correctOptionId;

    /**
     * Text that is shown when a user chooses an incorrect
     * answer or taps on the lamp icon in a quiz-style poll,
     * 0-200 characters with at most 2 line feeds after entities parsing
     */
    @JsonProperty("explanation")
    private String explanation;

    /**
     * Mode for parsing entities in the explanation.
     * See <a href="https://core.telegram.org/bots/api#formatting-options">formatting options</a> for more details.
     */
    @JsonProperty("explanation_parse_mode")
    private String explanationParseMode;

    /**
     * A JSON-serialized list of special entities
     * that appear in the poll explanation.
     * It can be specified instead of explanation_parse_mode
     */
    @JsonProperty("explanation_entities")
    private List<MessageEntity> explanationEntities;

    /**
     * Amount of time in seconds the poll will be active
     * after creation, 5-600. Can't be used together with close_date.
     */
    @JsonProperty("open_period")
    private Integer openPeriod;

    /**
     * Point in time (Unix timestamp) when the poll
     * will be automatically closed. Must be at least 5
     * and no more than 600 seconds in the future.
     * Can't be used together with open_period.
     */
    @JsonProperty("close_date")
    private Integer closeDate;

    /**
     * Pass True if the poll needs to be immediately closed.
     * This can be useful for poll preview.
     */
    @JsonProperty("is_closed")
    private Boolean isClosed;

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
     * Unique identifier of the message effect to be added
     * to the message; for private chats only
     */
    @JsonProperty("message_effect_id")
    private String messageEffectId;

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
        if (question == null || question.isEmpty()) {
            throw new ApiValidationException("Question parameter can't be null or empty", this);
        }
        if (options == null) {
            throw new ApiValidationException("Options parameter can't be null or empty", this);
        }
        if (question.length() > 300) {
            throw new ApiValidationException("Question parameter must be in 1..300 range", this);
        }
        if (options.size() < 2 || options.size() > 10) {
            throw new ApiValidationException("Options size parameter must be in 2..10 range", this);
        }
        if (closeDate != null && openPeriod != null) {
            throw new ApiValidationException("Only one parameter allowed: closeDate, openPeriod", this);
        }
        if (replyMarkup != null) {
            replyMarkup.validate();
        }
    }
}

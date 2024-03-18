package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ReactionType;

import java.util.List;

/**
 * Use this method to change the chosen reactions on a message.
 * Service messages can't be reacted to. Automatically forwarded
 * messages from a channel to its discussion group have the same
 * available reactions as messages in the channel.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetMessageReaction implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Identifier of the target message. If the message
     * belongs to a media group, the reaction is set
     * to the first non-deleted message in the group instead.
     */
    @Required
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * New list of reaction types to set on the message.
     * Currently, as non-premium users, bots can set up
     * to one reaction per message. A custom emoji reaction
     * can be used if it is either already present on the message
     * or explicitly allowed by chat administrators.
     */
    @JsonProperty("reaction")
    private List<ReactionType> reaction;

    /**
     * Pass True to set the reaction with a big animation
     */
    @JsonProperty("is_big")
    private Boolean isBig;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

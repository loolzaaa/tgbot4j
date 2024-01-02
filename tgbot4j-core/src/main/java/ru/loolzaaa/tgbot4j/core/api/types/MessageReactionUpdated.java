package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This object represents a change of a reaction
 * on a message performed by a user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageReactionUpdated {
    /**
     * The chat containing the message the user reacted to
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Unique identifier of the message inside the chat
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Optional. The user that changed the reaction,
     * if the user isn't anonymous
     */
    @JsonProperty("user")
    private User user;

    /**
     * Optional. The chat on behalf of which the reaction
     * was changed, if the user is anonymous
     */
    @JsonProperty("actor_chat")
    private Chat actorChat;

    /**
     * Date of the change in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * Previous list of reaction types that were set by the user
     */
    @JsonProperty("old_reaction")
    private List<ReactionType> oldReaction;

    /**
     * New list of reaction types that have been set by the user
     */
    @JsonProperty("new_reaction")
    private List<ReactionType> newReaction;
}

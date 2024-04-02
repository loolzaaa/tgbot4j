package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.GameHighScore;

import java.util.List;

/**
 * Use this method to get data for high score tables.
 * Will return the score of the specified user
 * and several of their neighbors in a game.
 * Returns an Array of {@link GameHighScore} objects.
 * <p>
 * This method will currently return scores for the target user,
 * plus two of their closest neighbors on each side.
 * Will also return the top three users if the user
 * and their neighbors are not among them.
 * Please note that this behavior is subject to change.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGameHighScores implements TelegramMethod<List<GameHighScore>> {
    /**
     * Target user id
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Required if inline_message_id is not specified.
     * Unique identifier for the target chat
     */
    @JsonProperty("chat_id")
    private Integer chatId;

    /**
     * Required if inline_message_id is not specified.
     * Identifier of the sent message
     */
    @JsonProperty("message_id")
    private Integer messageId;

    /**
     * Required if chat_id and message_id are not specified.
     * Identifier of the inline message
     */
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    @Override
    public List<GameHighScore> determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeCollectionResponse(mapper, resultNode, GameHighScore.class);
    }
}

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
 * Deletes a story previously posted by the bot
 * on behalf of a managed business account.
 * Requires the can_manage_stories business bot right.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStory implements TelegramMethod<Boolean> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier of the story to edit
     */
    @Required
    @JsonProperty("story_id")
    private Integer storyId;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (storyId == null) {
            throw new ApiValidationException("Story Id parameter can't be null", this);
        }
    }
}

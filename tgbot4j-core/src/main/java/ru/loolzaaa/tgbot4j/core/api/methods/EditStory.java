package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.InputStoryContent;
import ru.loolzaaa.tgbot4j.core.api.types.MessageEntity;
import ru.loolzaaa.tgbot4j.core.api.types.Story;
import ru.loolzaaa.tgbot4j.core.api.types.StoryArea;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

import java.util.List;

/**
 * Edits a story previously posted by the bot
 * on behalf of a managed business account.
 * Requires the can_manage_stories business bot right.
 * Returns {@link Story} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditStory implements TelegramMethod<Story> {
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

    /**
     * Content of the story
     */
    @Required
    @JsonProperty("content")
    private InputStoryContent content;

    /**
     * Caption of the story, 0-2048 characters
     * after entities parsing
     */
    @JsonProperty("caption")
    private String caption;

    /**
     * Mode for parsing entities in the story caption.
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
     * A JSON-serialized list of clickable areas
     * to be shown on the story
     */
    @JsonProperty("areas")
    private List<StoryArea> areas;

    @Override
    public Story determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Story.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (storyId == null) {
            throw new ApiValidationException("Story Id parameter can't be null", this);
        }
        if (content == null) {
            throw new ApiValidationException("Content parameter can't be null", this);
        }
        if (caption != null && caption.length() > 2048) {
            throw new ApiValidationException("Caption parameter must not exceed 2048", this);
        }
    }
}

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
 * Posts a story on behalf of a managed business account.
 * Requires the can_manage_stories business bot right.
 * Returns {@link Story} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostStory implements TelegramMethod<Story> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Content of the story
     */
    @Required
    @JsonProperty("content")
    private InputStoryContent content;

    /**
     * Period after which the story is moved
     * to the archive, in seconds;
     * must be one of {@code 6 * 3600}, {@code 12 * 3600}, {@code 86400},
     * or {@code 2 * 86400}
     */
    @Required
    @JsonProperty("active_period")
    private Integer activePeriod;

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

    /**
     * Pass True to keep the story accessible after it expires
     */
    @JsonProperty("post_to_chat_page")
    private Boolean postToChatPage;

    /**
     * Pass True if the content of the story must be protected
     * from forwarding and screenshotting
     */
    @JsonProperty("protect_content")
    private Boolean protectContent;

    @Override
    public Story determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Story.class);
    }

    @Override
    public void validate() {
        if (businessConnectionId == null) {
            throw new ApiValidationException("Business Connection Id parameter can't be null", this);
        }
        if (content == null) {
            throw new ApiValidationException("Content parameter can't be null", this);
        }
        if (activePeriod == null) {
            throw new ApiValidationException("Active Period parameter can't be null", this);
        }
        if ((activePeriod != (6 * 3600)) && (activePeriod != (12 * 3600)) && (activePeriod != 86400) && (activePeriod != (2 * 86400))) {
            throw new ApiValidationException("Active Period parameter must be '6 * 3600', '12 * 3600', '86400' or '2 * 86400'", this);
        }
        if (caption != null && caption.length() > 2048) {
            throw new ApiValidationException("Caption parameter must not exceed 2048", this);
        }
    }
}

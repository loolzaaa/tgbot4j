package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Story;

/**
 * Reposts a story on behalf of a business account
 * from another business account. Both business accounts
 * must be managed by the same bot, and the story on the source account
 * must have been posted (or reposted) by the bot.
 * Requires the can_manage_stories business bot right
 * for both business accounts. Returns {@link Story} on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepostStory implements TelegramMethod<Story> {
    /**
     * Unique identifier of the business connection
     */
    @Required
    @JsonProperty("business_connection_id")
    private String businessConnectionId;

    /**
     * Unique identifier of the chat which posted the story
     * that should be reposted
     */
    @Required
    @JsonProperty("from_chat_id")
    private Integer fromChatId;

    /**
     * Unique identifier of the story that should be reposted
     */
    @Required
    @JsonProperty("from_story_id")
    private Integer fromStoryId;

    /**
     * Period after which the story is moved to the archive,
     * in seconds; must be one of 6 * 3600, 12 * 3600, 86400, or 2 * 86400
     */
    @Required
    @JsonProperty("active_period")
    private Integer activePeriod;

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
}

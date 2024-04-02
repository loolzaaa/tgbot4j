package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ChatPermissions;
import ru.loolzaaa.tgbot4j.core.exception.ApiValidationException;

/**
 * Use this method to restrict a user in a supergroup.
 * The bot must be an administrator in the supergroup for this to work
 * and must have the appropriate administrator rights.
 * Pass True for all permissions to lift restrictions from a user.
 * Returns True on success.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrictChatMember implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the target group or username
     * of the target supergroup or channel (in the format {@code @channelusername})
     */
    @Required
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * Unique identifier of the target user
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * A JSON-serialized object for new user permissions
     */
    @Required
    @JsonProperty("permissions")
    private ChatPermissions permissions;

    /**
     * Pass True if chat permissions are set independently.
     * Otherwise, the can_send_other_messages and can_add_web_page_previews permissions
     * will imply the can_send_messages, can_send_audios, can_send_documents, can_send_photos,
     * can_send_videos, can_send_video_notes, and can_send_voice_notes permissions;
     * the can_send_polls permission will imply the can_send_messages permission.
     */
    @JsonProperty("use_independent_chat_permissions")
    private Boolean useIndependentChatPermissions;

    /**
     * Date when restrictions will be lifted for the user;
     * Unix time.
     * If user is restricted for more than 366 days or less than 30 seconds
     * from the current time, they are considered to be restricted forever
     */
    @JsonProperty("until_date")
    private Integer untilDate;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }

    @Override
    public void validate() {
        if (chatId == null) {
            throw new ApiValidationException("Chat ID parameter can't be null", this);
        }
        if (userId == null) {
            throw new ApiValidationException("User ID parameter can't be null", this);
        }
        if (permissions == null) {
            throw new ApiValidationException("ChatPermissions parameter can't be null", this);
        }
    }
}

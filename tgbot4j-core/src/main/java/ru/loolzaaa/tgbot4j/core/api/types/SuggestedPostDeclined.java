package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a service message about the rejection
 * of a suggested post.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedPostDeclined {
    /**
     * Optional. Message containing the suggested post.
     * Note that the {@link Message} object in this field
     * will not contain the reply_to_message field
     * even if it itself is a reply.
     */
    @JsonProperty("suggested_post_message")
    private Message suggestedPostMessage;

    /**
     * Optional. Comment with which the post was declined
     */
    @JsonProperty("comment")
    private String comment;
}

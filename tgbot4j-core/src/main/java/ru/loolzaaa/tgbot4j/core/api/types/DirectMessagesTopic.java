package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes a topic of a direct messages chat.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectMessagesTopic {
    /**
     * Unique identifier of the topic
     */
    @JsonProperty("topic_id")
    private Integer topicId;

    /**
     * Optional. Information about the user that created the topic.
     * Currently, it is always present
     */
    @JsonProperty("user")
    private User user;
}

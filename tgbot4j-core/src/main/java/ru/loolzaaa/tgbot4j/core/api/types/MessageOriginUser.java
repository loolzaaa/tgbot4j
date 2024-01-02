package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The message was originally sent by a known user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOriginUser implements MessageOrigin {
    /**
     * Type of the message origin, always “user”
     */
    @JsonProperty("type")
    private String type;

    /**
     * Date the message was sent originally in Unix time
     */
    @JsonProperty("date")
    private Integer date;

    /**
     * User that sent the message originally
     */
    @JsonProperty("sender_user")
    private User senderUser;
}

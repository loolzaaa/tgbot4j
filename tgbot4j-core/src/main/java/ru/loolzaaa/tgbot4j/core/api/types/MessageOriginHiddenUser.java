package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The message was originally sent by an unknown user.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOriginHiddenUser implements MessageOrigin {
    /**
     * Type of the message origin, always “hidden_user”
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
    @JsonProperty("sender_user_name")
    private String senderUserName;
}

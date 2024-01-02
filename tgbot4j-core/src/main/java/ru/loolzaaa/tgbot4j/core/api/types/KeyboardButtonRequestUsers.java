package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object defines the criteria used to request a suitable user.
 * The identifier of the selected user will be shared with the bot
 * when the corresponding button is pressed.
 * <a href="https://core.telegram.org/bots/features#chat-and-user-selection">More about requesting users »</a>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyboardButtonRequestUsers {
    /**
     * Signed 32-bit identifier of the request,
     * which will be received back in the {@link UsersShared} object.
     * Must be unique within the message
     */
    @JsonProperty("request_id")
    private Integer requestId;

    /**
     * Optional. Pass True to request bots, pass False
     * to request regular users. If not specified,
     * no additional restrictions are applied.
     */
    @JsonProperty("user_is_bot")
    private Boolean userIsBot;

    /**
     * Optional. Pass True to request premium users,
     * pass False to request non-premium users.
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty("user_is_premium")
    private Boolean userIsPremium;

    /**
     * Optional. The maximum number of users to be selected;
     * 1-10. Defaults to 1.
     */
    @JsonProperty("max_quantity")
    private Integer maxQuantity;
}

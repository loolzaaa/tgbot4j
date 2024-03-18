package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.Update;

/**
 * Once the user has confirmed their payment
 * and shipping details, the Bot API sends the final confirmation
 * in the form of an {@link Update} with the field pre_checkout_query.
 * Use this method to respond to such pre-checkout queries.
 * On success, True is returned.
 *
 * @apiNote The Bot API must receive an answer within 10 seconds after the pre-checkout query was sent.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPreCheckoutQuery implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the query to be answered
     */
    @Required
    @JsonProperty("pre_checkout_query_id")
    private String preCheckoutQueryId;

    /**
     * Specify True if everything is alright (goods are available, etc.)
     * and the bot is ready to proceed with the order.
     * Use False if there are any problems.
     */
    @Required
    @JsonProperty("ok")
    private Boolean ok;

    /**
     * Required if ok is False. Error message in human readable
     * form that explains the reason for failure to proceed
     * with the checkout (e.g. "Sorry, somebody just bought the last
     * of our amazing black T-shirts while you were busy filling out
     * your payment details. Please choose a different color or garment!").
     * Telegram will display this message to the user.
     */
    @JsonProperty("error_message")
    private String errorMessage;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

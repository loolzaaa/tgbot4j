package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.ShippingOption;
import ru.loolzaaa.tgbot4j.core.api.types.Update;

import java.util.List;

/**
 * If you sent an invoice requesting a shipping address
 * and the parameter is_flexible was specified,
 * the Bot API will send an {@link Update} with a shipping_query field to the bot.
 * Use this method to reply to shipping queries. On success, True is returned.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerShippingQuery implements TelegramMethod<Boolean> {
    /**
     * Unique identifier for the query to be answered
     */
    @Required
    @JsonProperty("shipping_query_id")
    private String shippingQueryId;

    /**
     * Pass True if delivery to the specified address is possible
     * and False if there are any problems (for example,
     * if delivery to the specified address is not possible)
     */
    @Required
    @JsonProperty("ok")
    private Boolean ok;

    /**
     * Required if ok is True. A JSON-serialized array
     * of available shipping options.
     */
    @JsonProperty("shipping_options")
    private List<ShippingOption> shippingOptions;

    /**
     * Required if ok is False. Error message in human readable
     * form that explains why it is impossible to complete the order
     * (e.g. "Sorry, delivery to your desired address is unavailable').
     * Telegram will display this message to the user.
     */
    @JsonProperty("error_message")
    private String errorMessage;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

package ru.loolzaaa.tgbot4j.core.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.loolzaaa.tgbot4j.core.api.Required;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.types.PassportElementError;

import java.util.List;

/**
 * Informs a user that some of the Telegram Passport elements
 * they provided contains errors. The user will not be able
 * to re-submit their Passport to you until the errors
 * are fixed (the contents of the field for
 * which you returned the error must change).
 * Returns True on success.
 * <p>
 * Use this if the data submitted by the user doesn't satisfy
 * the standards your service requires for any reason.
 * For example, if a birthday date seems invalid,
 * a submitted document is blurry, a scan shows evidence of tampering, etc.
 * Supply some details in the error message
 * to make sure the user knows how to correct the issues.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetPassportDataErrors implements TelegramMethod<Boolean> {
    /**
     * User identifier
     */
    @Required
    @JsonProperty("user_id")
    private Long userId;

    /**
     * A JSON-serialized array describing the errors
     */
    @Required
    @JsonProperty("errors")
    private List<PassportElementError> errors;

    @Override
    public Boolean determineResponseType(ObjectMapper mapper, JsonNode resultNode) {
        return deserializeObjectResponse(mapper, resultNode, Boolean.class);
    }
}

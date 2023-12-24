package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This object represents the content of a message
 * to be sent as a result of an inline query.
 * Telegram clients currently support the following 5 types:
 * <ul>
 *     <li>{@link InputTextMessageContent}</li>
 *     <li>{@link InputLocationMessageContent}</li>
 *     <li>{@link InputVenueMessageContent}</li>
 *     <li>{@link InputContactMessageContent}</li>
 *     <li>{@link InputInvoiceMessageContent}</li>
 * </ul>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputTextMessageContent.class),
        @JsonSubTypes.Type(value = InputLocationMessageContent.class),
        @JsonSubTypes.Type(value = InputVenueMessageContent.class),
        @JsonSubTypes.Type(value = InputContactMessageContent.class),
        @JsonSubTypes.Type(value = InputInvoiceMessageContent.class),
})
public interface InputMessageContent {
}

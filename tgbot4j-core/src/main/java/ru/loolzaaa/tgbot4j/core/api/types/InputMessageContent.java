package ru.loolzaaa.tgbot4j.core.api.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputMessageContent {
}

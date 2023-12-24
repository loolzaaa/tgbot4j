package ru.loolzaaa.tgbot4j.core.api.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents an error in the Telegram Passport element
 * which was submitted that should be resolved by the user.
 * It should be one of:
 * <ul>
 *     <li>{@link PassportElementErrorDataField}</li>
 *     <li>{@link PassportElementErrorFrontSide}</li>
 *     <li>{@link PassportElementErrorReverseSide}</li>
 *     <li>{@link PassportElementErrorSelfie}</li>
 *     <li>{@link PassportElementErrorFile}</li>
 *     <li>{@link PassportElementErrorFiles}</li>
 *     <li>{@link PassportElementErrorTranslationFile}</li>
 *     <li>{@link PassportElementErrorTranslationFiles}</li>
 *     <li>{@link PassportElementErrorUnspecified}</li>
 * </ul>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportElementError {
}

package ru.loolzaaa.tgbot4j.core.api.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "source")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PassportElementErrorDataField.class, name = "data"),
        @JsonSubTypes.Type(value = PassportElementErrorFrontSide.class, name = "front_side"),
        @JsonSubTypes.Type(value = PassportElementErrorReverseSide.class, name = "reverse_side"),
        @JsonSubTypes.Type(value = PassportElementErrorSelfie.class, name = "selfie"),
        @JsonSubTypes.Type(value = PassportElementErrorFile.class, name = "file"),
        @JsonSubTypes.Type(value = PassportElementErrorFiles.class, name = "files"),
        @JsonSubTypes.Type(value = PassportElementErrorTranslationFile.class, name = "translation_file"),
        @JsonSubTypes.Type(value = PassportElementErrorTranslationFiles.class, name = "translation_files"),
        @JsonSubTypes.Type(value = PassportElementErrorUnspecified.class, name = "unspecified"),
})
public interface PassportElementError {
}

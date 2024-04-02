package ru.loolzaaa.tgbot4j.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines the method of serializing
 * the API object/method field that will be sent
 * by a request with the multipart content type.
 *
 * @see TelegramMultipartMethod
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartType {
    /**
     * Type of field serialization.
     *
     * @return serialization type
     */
    Type value() default Type.TEXT;

    /**
     * Available serialization multipart types:
     * <ul>
     *     <li>TEXT - serialize as simple text</li>
     *     <li>JSON - serialize as string JSON representation</li>
     *     <li>BINARY - serialize as byte representation</li>
     * </ul>
     */
    enum Type {
        TEXT, JSON, BINARY
    }
}

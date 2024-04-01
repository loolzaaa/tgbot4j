package ru.loolzaaa.tgbot4j.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines required fields
 * for API methods that will be sent by a request.
 * <p>
 * Placing this annotation on API objects has no effect.
 * To validate API objects, you need to implement
 * the {@link Validated#validate()}.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    boolean useConstraints() default false;

    long min() default 0;

    long max() default 0;
}

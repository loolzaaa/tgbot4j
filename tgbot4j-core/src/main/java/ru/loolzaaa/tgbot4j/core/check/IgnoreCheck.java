package ru.loolzaaa.tgbot4j.core.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used for ignoring some
 * of the API Specification checks.
 *
 * @see ApiSpecificationChecker
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCheck {
}

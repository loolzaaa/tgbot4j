package ru.loolzaaa.tgbot4j.core.api;

/**
 * An interface that allows you to describe the validation process.
 * <p>
 * Can (but should not for objects) be implemented on both API objects
 * and methods.
 */

public interface Validated {
    /**
     * Method describing the validation process
     */
    default void validate() {
    }
}

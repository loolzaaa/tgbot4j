package ru.loolzaaa.tgbot4j.util.backoff;

/**
 * Provides next Backoff interval with reset feature.
 *
 * <p>Users of this interface are expected to use it like this:
 *
 * <pre class="code">
 * // In some operation that can failed:
 * try {
 *     // do some stuff
 * } catch (Exception e) {
 *     long waitInterval = backOff.nextBackOff();
 *     // sleep, for example, Thread.sleep(waitInterval)
 *     // retry (if applicable) operation
 * }</pre>
 * <p>
 * Once the underlying operation has completed successfully,
 * the backoff should be reset.
 */
public interface BackOff {
    /**
     * Specify the next backoff interval in milliseconds.
     */
    long nextBackOff();

    /**
     * Resets a current backoff interval to initial value.
     */
    void reset();
}

package ru.loolzaaa.tgbot4j.util.backoff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation of {@link BackOff} that increases the back off period for each
 * retry attempt. When the interval has reached the {@linkplain #setMaxInterval
 * max interval}, it is no longer increased.
 *
 * <p>Example: The default interval is {@value #DEFAULT_INITIAL_INTERVAL} ms;
 * the default multiplier is {@value #DEFAULT_MULTIPLIER}; and the default max
 * interval is {@value #DEFAULT_MAX_INTERVAL}. For 10 attempts, the sequence will be
 * as follows:
 *
 * <pre>
 * request#     back off
 *
 *  1              2000
 *  2              3000
 *  3              4500
 *  4              6750
 *  5             10125
 *  6             15187
 *  7             22780
 *  8             30000
 *  9             30000
 * 10             30000
 * </pre>
 */

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ExponentialBackOff implements BackOff {

    /**
     * The default initial interval.
     */
    public static final long DEFAULT_INITIAL_INTERVAL = 500L;

    /**
     * The default multiplier (increases the interval by 50%).
     */
    public static final double DEFAULT_MULTIPLIER = 1.5;

    /**
     * The default maximum back off time.
     */
    public static final long DEFAULT_MAX_INTERVAL = 60000L;

    private long initialInterval = DEFAULT_INITIAL_INTERVAL;

    private double multiplier = DEFAULT_MULTIPLIER;

    private long maxInterval = DEFAULT_MAX_INTERVAL;

    private long currentInterval = -1;

    /**
     * Create an instance with the supplied settings.
     *
     * @param initialInterval the initial interval in milliseconds
     * @param multiplier      the multiplier (should be greater than or equal to 1)
     */
    public ExponentialBackOff(long initialInterval, double multiplier) {
        if (multiplier < 1) {
            throw new IllegalArgumentException("Incorrect multiplier. Expected greater than or equal 1. Actual: " + multiplier);
        }
        this.initialInterval = initialInterval;
        this.multiplier = multiplier;
    }

    @Override
    public long nextBackOff() {
        if (currentInterval >= maxInterval) {
            return maxInterval;
        } else if (currentInterval < 0) {
            currentInterval = Math.min(initialInterval, maxInterval);
        } else {
            long newInterval = (long) (currentInterval * multiplier);
            currentInterval = Math.min(newInterval, maxInterval);
        }
        return currentInterval;
    }

    @Override
    public void reset() {
        currentInterval = -1;
    }
}

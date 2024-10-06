package ru.loolzaaa.tgbot4j.util.backoff;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class ExponentialBackOffTest {
    @Test
    void shouldNotExceedMaxInterval() {
        ExponentialBackOff backOff = new ExponentialBackOff(2000, 1.5);
        backOff.setMaxInterval(30000);

        int maxIntervalReachedCounter = 0;
        long nextInterval = 0;
        long firstInterval = 0;
        for (int i = 1; i <= 10; i++) {
            nextInterval = backOff.nextBackOff();
            if (i == 1) {
                firstInterval = nextInterval;
            }
            if (nextInterval == backOff.getMaxInterval()) {
                maxIntervalReachedCounter++;
            }
            System.out.printf("%d\t\t\t%d%n", i, nextInterval);
        }

        assertTrue(maxIntervalReachedCounter > 0);
        assertTrue(nextInterval <= backOff.getMaxInterval());

        backOff.reset();
        nextInterval = backOff.nextBackOff();

        assertEquals(firstInterval, nextInterval);
    }
}
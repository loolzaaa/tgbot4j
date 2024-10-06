package ru.loolzaaa.tgbot4j.bot.receiver;

import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.util.backoff.ExponentialBackOff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongPollingUpdateReceiverIT {

    static final String BOT_TEST_TOKEN = System.getenv("BOT_TEST_TOKEN");

    LongPollingUpdateReceiver updateReceiver;

    FakeUpdateSupplier updateSupplier;

    CountDownLatch countDownLatch;

    @BeforeAll
    static void prepare() {
        if (BOT_TEST_TOKEN == null) {
            throw new NullPointerException("BOT_TEST_TOKEN variable is null");
        }
    }

    @BeforeEach
    void setUp() {
        LongPollingUpdateReceiver.ReceiverOptions options = new LongPollingUpdateReceiver.ReceiverOptions();
        options.setReceiverTaskDelay(1);

        updateSupplier = new FakeUpdateSupplier();
        updateReceiver = new LongPollingUpdateReceiver("TEST", BOT_TEST_TOKEN, updateSupplier, options);
        updateReceiver.start(new ConcurrentLinkedDeque<>());

        countDownLatch = new CountDownLatch(1);
    }

    @AfterEach
    void tearDown() {
        updateReceiver.stop();
    }

    @Test
    void shouldUseBackOffWhenGetUpdates() throws Exception {
        countDownLatch.await();

        List<Long> usedWaitIntervals = updateSupplier.getUsedWaitIntervals();

        assertEquals(10, usedWaitIntervals.size());
        assertEquals(100, usedWaitIntervals.get(0));
        assertEquals(-1, usedWaitIntervals.get(usedWaitIntervals.size() - 1));

        long lastValue = Long.MIN_VALUE;
        for (long value : usedWaitIntervals) {
            if (value == -1) {
                break;
            }
            assertTrue(value > lastValue);
        }
    }

    class FakeUpdateSupplier implements Supplier<List<Update>> {

        final ExponentialBackOff backOff = new ExponentialBackOff(100, 1.5);

        @Getter
        final List<Long> usedWaitIntervals = new ArrayList<>();

        int requestCounter = 0;

        @Override
        public List<Update> get() {
            requestCounter++;
            try {
                if (requestCounter <= 4) {
                    throw new RuntimeException();
                }
                backOff.reset();
                usedWaitIntervals.add(backOff.getCurrentInterval());
                if (requestCounter == 10) {
                    countDownLatch.countDown();
                }
                return Collections.emptyList();
            } catch (Exception e) {
                long waitInterval = backOff.nextBackOff();
                usedWaitIntervals.add(waitInterval);
                try {
                    Thread.sleep(waitInterval);
                } catch (InterruptedException ignored) {
                }
                return Collections.emptyList();
            }
        }
    }
}
package ru.loolzaaa.tgbot4j.bot.sender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.loolzaaa.tgbot4j.core.api.methods.GetMe;
import ru.loolzaaa.tgbot4j.core.api.types.User;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultMethodSenderIT {

    static final String BOT_TEST_TOKEN = System.getenv("BOT_TEST_TOKEN");

    DefaultMethodSender methodSender;

    @BeforeAll
    static void prepare() {
        if (BOT_TEST_TOKEN == null) {
            throw new NullPointerException("BOT_TEST_TOKEN variable is null");
        }
    }

    @BeforeEach
    void setUp() {
        methodSender = new DefaultMethodSender(BOT_TEST_TOKEN, null);
    }

    @Test
    void shouldThrowExceptionWhileSyncValidateMethod() {
        ExceptionalGetMe exceptionalGetMe = new ExceptionalGetMe();

        assertThrows(RuntimeException.class, () -> methodSender.send(exceptionalGetMe));
    }

    @Test
    void shouldThrowExceptionWhileAsyncValidateMethod() {
        ExceptionalGetMe exceptionalGetMe = new ExceptionalGetMe();

        assertThrows(ExecutionException.class, () -> methodSender.sendAsync(exceptionalGetMe).get());
    }

    @Test
    void shouldCorrectSyncReceiveInformation() {
        final Long botId = Long.parseLong(BOT_TEST_TOKEN.split(":")[0]);
        GetMe getMe = new GetMe();

        User user = methodSender.send(getMe);

        assertEquals(botId, user.getId());
    }

    @Test
    void shouldCorrectAsyncReceiveInformation() throws Exception {
        final Long botId = Long.parseLong(BOT_TEST_TOKEN.split(":")[0]);
        GetMe getMe = new GetMe();

        User user = methodSender.sendAsync(getMe).get();

        assertEquals(botId, user.getId());
    }

    static class ExceptionalGetMe extends GetMe {
        @Override
        public void validate() {
            throw new RuntimeException();
        }
    }
}
package ru.loolzaaa.tgbot4j.config;

import ru.loolzaaa.tgbot4j.bot.receiver.LongPollingUpdateReceiver;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;

import java.util.Objects;

public class ReceiverConfigurer extends BotConfigurer<UpdateReceiver> {
    public ReceiverConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    @Override
    public UpdateReceiver configure() {
        return Objects.requireNonNullElseGet(botComponent, () -> new LongPollingUpdateReceiver(botName, botToken, null, null));
    }
}

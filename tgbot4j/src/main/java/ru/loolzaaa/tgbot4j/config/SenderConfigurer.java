package ru.loolzaaa.tgbot4j.config;

import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.Objects;

public class SenderConfigurer extends BotConfigurer<MethodSender> {
    public SenderConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    @Override
    public MethodSender configure() {
        return Objects.requireNonNullElseGet(botComponent, () -> new DefaultMethodSender(botToken, null));
    }
}

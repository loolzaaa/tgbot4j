package ru.loolzaaa.tgbot4j;

import lombok.NonNull;
import ru.loolzaaa.tgbot4j.bot.TelegramBotImpl;
import ru.loolzaaa.tgbot4j.config.ReceiverConfigurer;
import ru.loolzaaa.tgbot4j.config.SenderConfigurer;
import ru.loolzaaa.tgbot4j.core.bot.TelegramBot;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.function.Consumer;

public class TelegramBotBuilder {

    private final String botName;
    private final String botToken;

    private final SenderConfigurer senderConfigurer;

    private final ReceiverConfigurer receiverConfigurer;

    private TelegramBotBuilder(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
        this.senderConfigurer = new SenderConfigurer(botName, botToken);
        this.receiverConfigurer = new ReceiverConfigurer(botName, botToken);
    }

    public static TelegramBotBuilder init(@NonNull String name, @NonNull String token) {
        return new TelegramBotBuilder(name, token);
    }

    public TelegramBotBuilder methodSender(Consumer<SenderConfigurer> senderCustomizer) {
        senderCustomizer.accept(this.senderConfigurer);
        return this;
    }

    public TelegramBotBuilder updateReceiver(Consumer<ReceiverConfigurer> receiverCustomizer) {
        receiverCustomizer.accept(this.receiverConfigurer);
        return this;
    }

    public TelegramBot build() {
        MethodSender methodSender = senderConfigurer.configure();
        UpdateReceiver updateReceiver = receiverConfigurer.configure();
        return new TelegramBotImpl(botName, methodSender, updateReceiver);
    }
}

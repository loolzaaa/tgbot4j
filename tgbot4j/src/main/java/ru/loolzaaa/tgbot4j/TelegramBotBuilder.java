package ru.loolzaaa.tgbot4j;

import lombok.NonNull;
import ru.loolzaaa.tgbot4j.bot.TelegramBotImpl;
import ru.loolzaaa.tgbot4j.config.ReceiverConfigurer;
import ru.loolzaaa.tgbot4j.config.SenderConfigurer;
import ru.loolzaaa.tgbot4j.core.bot.TelegramBot;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.function.Consumer;

/**
 * Main builder for Telegram bots.
 * <p>
 * Any Telegram bot can be easily created
 * and customized with this builder.
 *
 * <pre>{@code
 *     TelegramBot bot = TelegramBotBuilder.init("BOT", botToken)
 *         .updateReceiver(receiverConfigurer -> {
 *             receiverConfigurer.longPolling(longPollingConfigurer -> {
 *                 longPollingConfigurer.withOptions(receiverOptions -> {
 *                     receiverOptions.setUpdateTimeout(10);
 *                 });
 *             });
 *         })
 *         .build();
 * }</pre>
 */

public class TelegramBotBuilder {

    private final String botName;

    private final SenderConfigurer senderConfigurer;

    private final ReceiverConfigurer receiverConfigurer;

    private TelegramBotBuilder(@NonNull String botName, @NonNull String botToken) {
        this.botName = botName;
        this.senderConfigurer = new SenderConfigurer(botName, botToken);
        this.receiverConfigurer = new ReceiverConfigurer(botName, botToken);
    }

    /**
     * Initialization of Telegram bot builder.
     *
     * @param name  bot name
     * @param token bot token
     * @return this telegram bot builder
     */
    public static TelegramBotBuilder init(@NonNull String name, @NonNull String token) {
        return new TelegramBotBuilder(name, token);
    }

    /**
     * Customization for API method sender.
     *
     * @param senderCustomizer method sender customizer consumer
     * @return this telegram bot builder
     * @see SenderConfigurer
     */
    public TelegramBotBuilder methodSender(Consumer<SenderConfigurer> senderCustomizer) {
        senderCustomizer.accept(this.senderConfigurer);
        return this;
    }

    /**
     * Customization for update receiver.
     *
     * @param receiverCustomizer update receiver customizer consumer
     * @return this telegram bot builder
     * @see ReceiverConfigurer
     */
    public TelegramBotBuilder updateReceiver(Consumer<ReceiverConfigurer> receiverCustomizer) {
        receiverCustomizer.accept(this.receiverConfigurer);
        return this;
    }

    /**
     * Finish Telegram bot building process.
     *
     * @return configured Telegram bot
     */
    public TelegramBot build() {
        MethodSender methodSender = senderConfigurer.configure();
        UpdateReceiver updateReceiver = receiverConfigurer.configure();
        return new TelegramBotImpl(botName, methodSender, updateReceiver);
    }
}

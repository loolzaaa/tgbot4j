package ru.loolzaaa.tgbot4j.config;

import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.function.Consumer;

/**
 * Method sender fluent configurer.
 * <p>
 * Allow creates default or user defined method sender.
 * <p>
 * Default method sender can be configured
 * with options separately.
 * <p>
 * All methods of this configurer returns this instance.
 */

public final class SenderConfigurer extends BotConfigurer<MethodSender> {

    private ComponentConfigurer<? extends MethodSender> methodSenderConfigurer;

    /**
     * Constructor creates configurer with defined bot name and token.
     *
     * @param botName  bot name
     * @param botToken bot token
     */
    public SenderConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    /**
     * Default sender configurer.
     * <p>
     * Creates {@link DefaultSenderConfigurer} for further
     * sender options change.
     *
     * @param defaultSenderConfigurerCustomizer sender customizer
     * @return this sender configurer
     */
    public SenderConfigurer defaultSender(Consumer<DefaultSenderConfigurer> defaultSenderConfigurerCustomizer) {
        DefaultSenderConfigurer defaultSenderConfigurer = new DefaultSenderConfigurer();
        methodSenderConfigurer = defaultSenderConfigurer;
        defaultSenderConfigurerCustomizer.accept(defaultSenderConfigurer);
        return this;
    }

    /**
     * User defined sender configurer.
     *
     * @param methodSender custom method sender
     * @return this sender configurer
     */
    public SenderConfigurer customSender(MethodSender methodSender) {
        methodSenderConfigurer = () -> methodSender;
        return this;
    }

    /**
     * Configure method sender for Telegram bot.
     * <p>
     * If current method sender configurer is null,
     * creates default.
     *
     * @return configured method sender
     */
    @Override
    public MethodSender configure() {
        if (methodSenderConfigurer == null) {
            return new DefaultMethodSender(botToken, null);
        }
        return methodSenderConfigurer.configure();
    }

    /**
     * Default method sender configurer.
     * <p>
     * Allow changes default method sender options
     * before create it.
     */
    public class DefaultSenderConfigurer implements ComponentConfigurer<DefaultMethodSender> {

        private DefaultMethodSender.SenderOptions options;

        /**
         * Configure default method sender options.
         *
         * @param optionsCustomizer options customizer
         * @return this default sender configurer
         */
        public DefaultSenderConfigurer withOptions(Consumer<DefaultMethodSender.SenderOptions> optionsCustomizer) {
            this.options = new DefaultMethodSender.SenderOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        /**
         * Creates new default method sender with defined options.
         *
         * @return configured default method sender
         */
        @Override
        public DefaultMethodSender configure() {
            return new DefaultMethodSender(botToken, options);
        }
    }
}

package ru.loolzaaa.tgbot4j.config;

import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.function.Consumer;

public final class SenderConfigurer extends BotConfigurer<MethodSender> {

    private ComponentConfigurer<? extends MethodSender> methodSenderConfigurer;

    public SenderConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    public SenderConfigurer defaultSender(Consumer<DefaultSenderConfigurer> defaultSenderConfigurerCustomizer) {
        DefaultSenderConfigurer defaultSenderConfigurer = new DefaultSenderConfigurer();
        methodSenderConfigurer = defaultSenderConfigurer;
        defaultSenderConfigurerCustomizer.accept(defaultSenderConfigurer);
        return this;
    }

    public SenderConfigurer customSender(MethodSender methodSender) {
        methodSenderConfigurer = () -> methodSender;
        return this;
    }

    @Override
    public MethodSender configure() {
        if (methodSenderConfigurer == null) {
            return new DefaultMethodSender(botToken, null);
        }
        return methodSenderConfigurer.configure();
    }

    public class DefaultSenderConfigurer implements ComponentConfigurer<DefaultMethodSender> {

        private DefaultMethodSender.SenderOptions options;

        public DefaultSenderConfigurer withOptions(Consumer<DefaultMethodSender.SenderOptions> optionsCustomizer) {
            this.options = new DefaultMethodSender.SenderOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        @Override
        public DefaultMethodSender configure() {
            return new DefaultMethodSender(botToken, options);
        }
    }
}

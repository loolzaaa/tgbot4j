package ru.loolzaaa.tgbot4j.config;

import ru.loolzaaa.tgbot4j.bot.receiver.LongPollingUpdateReceiver;
import ru.loolzaaa.tgbot4j.bot.receiver.ServerlessWebhookUpdateReceiver;
import ru.loolzaaa.tgbot4j.bot.receiver.WebhookUpdateReceiver;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ReceiverConfigurer extends BotConfigurer<UpdateReceiver> {

    private ComponentConfigurer<? extends UpdateReceiver> updateReceiverConfigurer;

    public ReceiverConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    public ReceiverConfigurer longPolling(Consumer<LongPollingConfigurer> longPollingConfigurerCustomizer) {
        LongPollingConfigurer longPollingConfigurer = new LongPollingConfigurer();
        updateReceiverConfigurer = longPollingConfigurer;
        longPollingConfigurerCustomizer.accept(longPollingConfigurer);
        return this;
    }

    public ReceiverConfigurer webhook(Consumer<WebhookConfigurer> webhookConfigurerCustomizer) {
        WebhookConfigurer webhookConfigurer = new WebhookConfigurer();
        updateReceiverConfigurer = webhookConfigurer;
        webhookConfigurerCustomizer.accept(webhookConfigurer);
        return this;
    }

    public ReceiverConfigurer serverlessWebhook(Consumer<ServerlessWebhookConfigurer> serverlessWebhookConfigurerCustomizer) {
        ServerlessWebhookConfigurer serverlessWebhookConfigurer = new ServerlessWebhookConfigurer();
        updateReceiverConfigurer = serverlessWebhookConfigurer;
        serverlessWebhookConfigurerCustomizer.accept(serverlessWebhookConfigurer);
        return this;
    }

    public ReceiverConfigurer customReceiver(UpdateReceiver updateReceiver) {
        updateReceiverConfigurer = () -> updateReceiver;
        return this;
    }

    @Override
    public UpdateReceiver configure() {
        if (updateReceiverConfigurer == null) {
            return new LongPollingUpdateReceiver(botName, botToken, null, null);
        }
        return updateReceiverConfigurer.configure();
    }

    public class LongPollingConfigurer implements ComponentConfigurer<LongPollingUpdateReceiver> {

        private Supplier<List<Update>> updateSupplier;

        private LongPollingUpdateReceiver.ReceiverOptions options;

        public LongPollingConfigurer withUpdateSupplier(Supplier<List<Update>> updateSupplier) {
            this.updateSupplier = updateSupplier;
            return this;
        }

        public LongPollingConfigurer withOptions(Consumer<LongPollingUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new LongPollingUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        @Override
        public LongPollingUpdateReceiver configure() {
            return new LongPollingUpdateReceiver(botName, botToken, updateSupplier, options);
        }
    }

    public class WebhookConfigurer implements ComponentConfigurer<WebhookUpdateReceiver> {

        private SetWebhook setWebhook;

        private WebhookUpdateReceiver.ReceiverOptions options;

        public WebhookConfigurer withSetWebhook(SetWebhook setWebhook) {
            this.setWebhook = setWebhook;
            return this;
        }

        public WebhookConfigurer withOptions(Consumer<WebhookUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new WebhookUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        @Override
        public WebhookUpdateReceiver configure() {
            return new WebhookUpdateReceiver(botName, botToken, setWebhook, options);
        }
    }

    public class ServerlessWebhookConfigurer implements ComponentConfigurer<ServerlessWebhookUpdateReceiver> {

        private SetWebhook setWebhook;

        private ServerlessWebhookUpdateReceiver.UpdateHandler updateHandler;

        private ServerlessWebhookUpdateReceiver.ReceiverOptions options;

        public ServerlessWebhookConfigurer withSetWebhook(SetWebhook setWebhook) {
            this.setWebhook = setWebhook;
            return this;
        }

        public ServerlessWebhookConfigurer withUpdateHandler(ServerlessWebhookUpdateReceiver.UpdateHandler updateHandler) {
            this.updateHandler = updateHandler;
            return this;
        }

        public ServerlessWebhookConfigurer withOptions(Consumer<ServerlessWebhookUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new ServerlessWebhookUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        @Override
        public ServerlessWebhookUpdateReceiver configure() {
            return new ServerlessWebhookUpdateReceiver(botName, botToken, setWebhook, updateHandler, options);
        }
    }
}

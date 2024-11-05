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

/**
 * Update receiver fluent configurer.
 * <p>
 * Allow creates one of predefined
 * or user defined update receiver.
 * <p>
 * Any of predefined update receivers can be configured
 * with options separately.
 * <p>
 * All methods of this configurer returns this instance.
 */

public final class ReceiverConfigurer extends BotConfigurer<UpdateReceiver> {

    private ComponentConfigurer<? extends UpdateReceiver> updateReceiverConfigurer;

    /**
     * Constructor creates configurer with defined bot name and token.
     *
     * @param botName  bot name
     * @param botToken bot token
     */
    public ReceiverConfigurer(String botName, String botToken) {
        super(botName, botToken);
    }

    /**
     * Apply long polling customizer.
     *
     * @param longPollingConfigurerCustomizer long polling customizer
     * @return current instance of {@link ReceiverConfigurer}
     */
    public ReceiverConfigurer longPolling(Consumer<LongPollingConfigurer> longPollingConfigurerCustomizer) {
        LongPollingConfigurer longPollingConfigurer = new LongPollingConfigurer();
        updateReceiverConfigurer = longPollingConfigurer;
        longPollingConfigurerCustomizer.accept(longPollingConfigurer);
        return this;
    }

    /**
     * Webhook update receiver configurer.
     * <p>
     * Creates {@link WebhookConfigurer} for further
     * receiver options change.
     *
     * @param webhookConfigurerCustomizer receiver customizer
     * @return this receiver configurer
     */
    public ReceiverConfigurer webhook(Consumer<WebhookConfigurer> webhookConfigurerCustomizer) {
        WebhookConfigurer webhookConfigurer = new WebhookConfigurer();
        updateReceiverConfigurer = webhookConfigurer;
        webhookConfigurerCustomizer.accept(webhookConfigurer);
        return this;
    }

    /**
     * Serverless update receiver configurer.
     * <p>
     * Creates {@link ServerlessWebhookConfigurer} for further
     * receiver options change.
     *
     * @param serverlessWebhookConfigurerCustomizer receiver customizer
     * @return this receiver configurer
     */
    public ReceiverConfigurer serverlessWebhook(Consumer<ServerlessWebhookConfigurer> serverlessWebhookConfigurerCustomizer) {
        ServerlessWebhookConfigurer serverlessWebhookConfigurer = new ServerlessWebhookConfigurer();
        updateReceiverConfigurer = serverlessWebhookConfigurer;
        serverlessWebhookConfigurerCustomizer.accept(serverlessWebhookConfigurer);
        return this;
    }

    /**
     * User defined receiver configurer.
     *
     * @param updateReceiver custom update receiver
     * @return this receiver configurer
     */
    public ReceiverConfigurer customReceiver(UpdateReceiver updateReceiver) {
        updateReceiverConfigurer = () -> updateReceiver;
        return this;
    }

    /**
     * Configure update receiver for Telegram bot.
     * <p>
     * If current update receiver configurer is null,
     * creates {@link LongPollingUpdateReceiver} with default options.
     *
     * @return configured update receiver
     */
    @Override
    public UpdateReceiver configure() {
        if (updateReceiverConfigurer == null) {
            return new LongPollingUpdateReceiver(botName, botToken, null, null);
        }
        return updateReceiverConfigurer.configure();
    }

    /**
     * Long polling update receiver configurer.
     * <p>
     * Allow changes default update receiver options
     * before create it.
     */
    public class LongPollingConfigurer implements ComponentConfigurer<LongPollingUpdateReceiver> {

        private Supplier<List<Update>> updateSupplier;

        private LongPollingUpdateReceiver.ReceiverOptions options;

        /**
         * Configure custom update supplier for
         * long polling update receiver.
         *
         * @param updateSupplier custom update supplier
         * @return this long polling update receiver configurer
         */
        public LongPollingConfigurer withUpdateSupplier(Supplier<List<Update>> updateSupplier) {
            this.updateSupplier = updateSupplier;
            return this;
        }

        /**
         * Configure default long polling update receiver options.
         *
         * @param optionsCustomizer options customizer
         * @return this long polling update receiver configurer
         */
        public LongPollingConfigurer withOptions(Consumer<LongPollingUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new LongPollingUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        /**
         * Creates new default long polling update receiver
         * with defined options.
         *
         * @return configured long polling update receiver
         */
        @Override
        public LongPollingUpdateReceiver configure() {
            return new LongPollingUpdateReceiver(botName, botToken, updateSupplier, options);
        }
    }

    /**
     * Webhook update receiver configurer.
     * <p>
     * Allow changes default update receiver options
     * before create it.
     */
    public class WebhookConfigurer implements ComponentConfigurer<WebhookUpdateReceiver> {

        private SetWebhook setWebhook;

        private WebhookUpdateReceiver.ReceiverOptions options;

        /**
         * Configure custom set webhook object for
         * webhook update receiver.
         *
         * @param setWebhook custom set webhook object
         * @return this webhook update receiver configurer
         */
        public WebhookConfigurer withSetWebhook(SetWebhook setWebhook) {
            this.setWebhook = setWebhook;
            return this;
        }

        /**
         * Configure default webhook update receiver options.
         *
         * @param optionsCustomizer options customizer
         * @return this webhook update receiver configurer
         */
        public WebhookConfigurer withOptions(Consumer<WebhookUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new WebhookUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        /**
         * Creates new default webhook update receiver
         * with defined options.
         *
         * @return configured webhook update receiver
         */
        @Override
        public WebhookUpdateReceiver configure() {
            return new WebhookUpdateReceiver(botName, botToken, setWebhook, options);
        }
    }

    /**
     * Serverless update receiver configurer.
     * <p>
     * Allow changes default update receiver options
     * before create it.
     */
    public class ServerlessWebhookConfigurer implements ComponentConfigurer<ServerlessWebhookUpdateReceiver> {

        private SetWebhook setWebhook;

        private ServerlessWebhookUpdateReceiver.UpdateHandler updateHandler;

        private ServerlessWebhookUpdateReceiver.ReceiverOptions options;

        /**
         * Configure custom set webhook object for
         * serverless update receiver.
         *
         * @param setWebhook custom set webhook object
         * @return this serverless update receiver configurer
         */
        public ServerlessWebhookConfigurer withSetWebhook(SetWebhook setWebhook) {
            this.setWebhook = setWebhook;
            return this;
        }

        /**
         * Configure custom update handler for serverless update receiver.
         *
         * @param updateHandler custom update handler
         * @return this serverless update receiver configurer
         */
        public ServerlessWebhookConfigurer withUpdateHandler(ServerlessWebhookUpdateReceiver.UpdateHandler updateHandler) {
            this.updateHandler = updateHandler;
            return this;
        }

        /**
         * Configure default serverless update receiver options.
         *
         * @param optionsCustomizer options customizer
         * @return this serverless update receiver configurer
         */
        public ServerlessWebhookConfigurer withOptions(Consumer<ServerlessWebhookUpdateReceiver.ReceiverOptions> optionsCustomizer) {
            this.options = new ServerlessWebhookUpdateReceiver.ReceiverOptions();
            optionsCustomizer.accept(this.options);
            return this;
        }

        /**
         * Creates new default serverless update receiver
         * with defined options.
         *
         * @return configured serverless update receiver
         */
        @Override
        public ServerlessWebhookUpdateReceiver configure() {
            return new ServerlessWebhookUpdateReceiver(botName, botToken, setWebhook, updateHandler, options);
        }
    }
}

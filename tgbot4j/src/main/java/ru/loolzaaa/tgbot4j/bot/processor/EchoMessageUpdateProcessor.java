package ru.loolzaaa.tgbot4j.bot.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.methods.SendMessage;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * Simple echo implementation of update processor.
 * <p>
 * May be useful for development purposes.
 */

public class EchoMessageUpdateProcessor implements UpdateProcessor {

    private static final Logger log = LoggerFactory.getLogger(EchoMessageUpdateProcessor.class);

    /**
     * This method just send new message to sender with echo message.
     *
     * @param update       fresh update from API server
     * @param methodSender sender for API server communication
     * @param chain        update processors chain
     */
    @Override
    public void process(Update update, MethodSender methodSender, UpdateProcessorChain chain) {
        if (update.getMessage() != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChat().getId().toString());
            sendMessage.setText(update.getMessage().getText());
            Message send = methodSender.send(sendMessage);
            log.info("Echo message: {}", send);
        }
        chain.doProcess(update, methodSender);
    }
}

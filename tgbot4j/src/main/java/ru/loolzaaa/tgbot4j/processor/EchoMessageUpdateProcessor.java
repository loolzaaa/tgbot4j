package ru.loolzaaa.tgbot4j.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.methods.SendMessage;
import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

public class EchoMessageUpdateProcessor implements UpdateProcessor {

    private static final Logger log = LoggerFactory.getLogger(EchoMessageUpdateProcessor.class);

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

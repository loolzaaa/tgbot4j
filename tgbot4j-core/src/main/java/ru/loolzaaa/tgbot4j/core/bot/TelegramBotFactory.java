package ru.loolzaaa.tgbot4j.core.bot;

import lombok.NonNull;
import ru.loolzaaa.tgbot4j.core.receiver.LongPollingUpdateReceiver;
import ru.loolzaaa.tgbot4j.core.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;

public class TelegramBotFactory {
    public static TelegramBot createLongPollingBot(@NonNull String name, @NonNull String token) {
        MethodSender methodSender = new DefaultMethodSender(token, null);
        UpdateReceiver updateReceiver = new LongPollingUpdateReceiver(name, token, null, null);
        return new TelegramBotImpl(name, methodSender, updateReceiver);
    }
}

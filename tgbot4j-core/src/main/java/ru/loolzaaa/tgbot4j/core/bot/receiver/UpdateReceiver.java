package ru.loolzaaa.tgbot4j.core.bot.receiver;


import ru.loolzaaa.tgbot4j.core.api.types.Update;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface UpdateReceiver {
    void start(ConcurrentLinkedDeque<Update> updates);
    void stop();
    boolean isRunning();
}

package ru.loolzaaa.tgbot4j.core.bot;


import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.sender.TelegramSender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractBot implements Bot {

    private final ConcurrentLinkedDeque<Update> updates = new ConcurrentLinkedDeque<>();

    private final UpdateReceiver updateReceiver;

    private final UpdateProcessorChain updateProcessorChain = new UpdateProcessorChain();

    private UpdateHandler updateHandler;

    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    private final TelegramSender telegramSender;

    private final String name;
    private final String token;

    public AbstractBot(UpdateReceiver updateReceiver, TelegramSender telegramSender, String name, String token) {
        this.updateReceiver = updateReceiver;
        this.telegramSender = telegramSender;
        this.name = name;
        this.token = token;
    }

    @Override
    public void init() {
        updateProcessorChain.init();

        isRunning.set(true);

        updateReceiver.start(updates);

        updateHandler = new UpdateHandler();
        updateHandler.start();
    }

    @Override
    public void registerUpdateProcessor(UpdateProcessor updateProcessor) {
        updateProcessorChain.addUpdateProcess(updateProcessor);
    }

    private class UpdateHandler extends Thread {
        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (isRunning.get()) {
                try {
                    List<Update> updates = getUpdateList();
                    if (updates.isEmpty()) {
                        synchronized (updates) {
                            updates.wait();
                            updates = getUpdateList();
                            if (updates.isEmpty()) {
                                continue;
                            }
                        }
                    }
                    for (Update update : updates) {
                        updateProcessorChain.doProcess(update, telegramSender);
                    }
                } catch (InterruptedException e) {
                    // log
                    interrupt();
                } catch (Exception e) {
                    // log
                }
            }
            // log
        }
    }

    private List<Update> getUpdateList() {
        List<Update> updates = new ArrayList<>();
        for (Iterator<Update> it = this.updates.iterator(); it.hasNext();) {
            updates.add(it.next());
            it.remove();
        }
        return updates;
    }
}

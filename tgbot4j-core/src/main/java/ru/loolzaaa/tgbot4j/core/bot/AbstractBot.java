package ru.loolzaaa.tgbot4j.core.bot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractBot implements Bot {

    private static final Logger log = LoggerFactory.getLogger(AbstractBot.class);

    private final ConcurrentLinkedDeque<Update> updates = new ConcurrentLinkedDeque<>();

    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    private final UpdateProcessorChain updateProcessorChain = new UpdateProcessorChain();

    private final UpdateReceiver updateReceiver;

    private final MethodSender methodSender;

    private final String name;

    private UpdateHandler updateHandler;

    public AbstractBot(UpdateReceiver updateReceiver, MethodSender methodSender, String name) {
        this.updateReceiver = updateReceiver;
        this.methodSender = methodSender;
        this.name = name;
    }

    @Override
    public synchronized void registerUpdateProcessor(UpdateProcessor updateProcessor) {
        updateProcessorChain.addUpdateProcess(updateProcessor);
    }

    @Override
    public synchronized void init() {
        //TODO: Is there a need to possible RE-RUN bot, or create new?
        if (isRunning.get()) {
            log.info("{} already initialized, do nothing", name);
            return;
        }

        isRunning.set(true);

        updateReceiver.start(updates);

        updateHandler = new UpdateHandler();
        updateHandler.start();
    }

    @Override
    public synchronized void destroy() {
        if (!isRunning.get()) {
            log.info("{} already destroyed, do nothing", name);
            return;
        }

        isRunning.set(false);

        updateHandler.interrupt();

        updateReceiver.stop();
    }

    private class UpdateHandler extends Thread {
        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (isRunning.get()) {
                try {
                    synchronized (updates) {
                        updates.wait();
                        List<Update> updates = getUpdateList();
                        if (updates.isEmpty()) {
                            continue;
                        }
                    }
                    for (Update update : updates) {
                        updateProcessorChain.doProcess(update, methodSender);
                    }
                } catch (InterruptedException e) {
                    log.debug("Interrupt {} update handler with message: {}", name, e.getLocalizedMessage());
                    interrupt();
                } catch (Exception e) {
                    log.error(e.getLocalizedMessage(), e);
                }
            }
            log.debug("{} update handler stopped", name);
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

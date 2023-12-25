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
import java.util.concurrent.atomic.AtomicInteger;

public class TelegramBotImpl implements TelegramBot {

    private static final Logger log = LoggerFactory.getLogger(TelegramBotImpl.class);

    private final ConcurrentLinkedDeque<Update> updates = new ConcurrentLinkedDeque<>();

    private final AtomicInteger runStatus = new AtomicInteger(0);

    private final UpdateHandler updateHandler = new UpdateHandler();

    private final UpdateProcessorChain updateProcessorChain = new UpdateProcessorChain();

    private final String name;

    private final MethodSender methodSender;

    private final UpdateReceiver updateReceiver;

    public TelegramBotImpl(String name, MethodSender methodSender, UpdateReceiver updateReceiver) {
        this.name = name;
        this.methodSender = methodSender;
        this.updateReceiver = updateReceiver;
    }

    @Override
    public synchronized void registerUpdateProcessor(UpdateProcessor updateProcessor) {
        updateProcessorChain.addUpdateProcess(updateProcessor);
    }

    @Override
    public synchronized void init() {
        if (runStatus.get() == 1) {
            log.info("{} already initialized, do nothing", name);
            return;
        } else if (runStatus.get() == 0) {
            throw new IllegalStateException(name + " already destroyed, create new instance");
        }
        runStatus.set(1);
        updateReceiver.start(updates);
        updateHandler.start();
    }

    @Override
    public synchronized void destroy() {
        if (runStatus.get() <= 0) {
            log.info("{} already destroyed or not initialized", name);
            return;
        }
        runStatus.set(-1);
        updateHandler.interrupt();
        updateReceiver.stop();
    }

    private class UpdateHandler extends Thread {
        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            while (runStatus.get() == 1) {
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

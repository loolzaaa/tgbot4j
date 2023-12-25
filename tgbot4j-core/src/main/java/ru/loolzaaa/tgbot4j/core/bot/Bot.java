package ru.loolzaaa.tgbot4j.core.bot;

import ru.loolzaaa.tgbot4j.core.processor.UpdateProcessor;

public interface Bot {
    void init();
    void registerUpdateProcessor(UpdateProcessor updateProcessor);
}

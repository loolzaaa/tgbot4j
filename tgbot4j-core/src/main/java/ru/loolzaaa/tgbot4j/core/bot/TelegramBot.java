package ru.loolzaaa.tgbot4j.core.bot;

import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;

public interface TelegramBot {
    void registerUpdateProcessor(UpdateProcessor updateProcessor);
    void init();
    void destroy();
}

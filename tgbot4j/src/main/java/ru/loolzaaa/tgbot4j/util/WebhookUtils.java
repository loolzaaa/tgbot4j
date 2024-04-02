package ru.loolzaaa.tgbot4j.util;

import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.api.methods.DeleteWebhook;
import ru.loolzaaa.tgbot4j.core.api.methods.GetWebhookInfo;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * Utilizing work with Telegram bot webhooks.
 */

public final class WebhookUtils {
    /**
     * Get information about existing webhook for Telegram bot.
     * <p>
     * If argument method sender is null, creates default.
     *
     * @param botToken     bot token
     * @param methodSender API method sender for communication
     * @return information about existing webhook
     */
    public static WebhookInfo getWebhook(String botToken, MethodSender methodSender) {
        GetWebhookInfo getWebhookInfo = new GetWebhookInfo();
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(getWebhookInfo);
    }

    /**
     * Set new webhook for Telegram bot.
     * <p>
     * If argument method sender is null, creates default.
     *
     * @param botToken     bot token
     * @param setWebhook   new set webhook API object
     * @param methodSender API method sender for communication
     * @return true if webhook successfully set, else false
     */
    public static boolean setWebhook(String botToken, SetWebhook setWebhook, MethodSender methodSender) {
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(setWebhook);
    }

    /**
     * Delete existing webhook for Telegram bot.
     * <p>
     * If argument method sender is null, creates default.
     *
     * @param botToken           bot token
     * @param dropPendingUpdated is need to drop unreceived updates?
     * @param methodSender       API method sender for communication
     * @return true if webhook successfully deleted, else false
     */
    public static boolean deleteWebhook(String botToken, boolean dropPendingUpdated, MethodSender methodSender) {
        DeleteWebhook deleteWebhook = new DeleteWebhook(dropPendingUpdated);
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(deleteWebhook);
    }
}

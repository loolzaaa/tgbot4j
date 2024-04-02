package ru.loolzaaa.tgbot4j.util;

import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.api.methods.DeleteWebhook;
import ru.loolzaaa.tgbot4j.core.api.methods.GetWebhookInfo;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

public final class WebhookUtils {
    public static WebhookInfo getWebhook(String botToken, MethodSender methodSender) {
        GetWebhookInfo getWebhookInfo = new GetWebhookInfo();
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(getWebhookInfo);
    }

    public static boolean setWebhook(String botToken, SetWebhook setWebhook, MethodSender methodSender) {
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(setWebhook);
    }

    public static boolean deleteWebhook(String botToken, boolean dropPendingUpdated, MethodSender methodSender) {
        DeleteWebhook deleteWebhook = new DeleteWebhook(dropPendingUpdated);
        if (methodSender == null) {
            methodSender = new DefaultMethodSender(botToken, null);
        }
        return methodSender.send(deleteWebhook);
    }
}

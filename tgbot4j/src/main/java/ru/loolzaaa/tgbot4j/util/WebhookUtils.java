package ru.loolzaaa.tgbot4j.util;

import ru.loolzaaa.tgbot4j.core.api.methods.DeleteWebhook;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;
import ru.loolzaaa.tgbot4j.sender.SyncMethodSender;

public class WebhookUtils {
    public static boolean setWebhook(String botToken, SetWebhook setWebhook) {
        MethodSender methodSender = new SyncMethodSender(botToken, null);
        return methodSender.send(setWebhook);
    }

    public static boolean deleteWebhook(String botToken, boolean dropPendingUpdated) {
        DeleteWebhook deleteWebhook = new DeleteWebhook(dropPendingUpdated);
        MethodSender methodSender = new SyncMethodSender(botToken, null);
        return methodSender.send(deleteWebhook);
    }
}

package ru.loolzaaa.tgbot4j.core.check;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class download last version
 * of Telegram Bot API documentation page.
 */

public class BotApiArchiveUpdater {
    /**
     * Hardcoded URL for Telegram Bot API page
     */
    private static final String BOT_API_URL = "https://core.telegram.org/bots/api";

    public static void main(String[] args) throws IOException {
        LocalDate now = LocalDate.now();
        Document doc = Jsoup.connect(BOT_API_URL).get();
        Elements devPageContent = doc.selectFirst("#dev_page_content").children();
        Files.writeString(
                Paths.get("archive", "bot_api_" + now.format(DateTimeFormatter.ISO_DATE)),
                devPageContent.toString(),
                StandardCharsets.UTF_8);
    }
}

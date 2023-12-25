package ru.loolzaaa.tgbot4j.core.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.loolzaaa.tgbot4j.core.Constants.*;

public class DefaultTelegramSender implements TelegramSender {

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient httpClient;

    private final String botToken;

    private final SenderOptions options;

    public DefaultTelegramSender(String botToken, SenderOptions options) {
        this.botToken = botToken;
        this.options = Objects.requireNonNullElseGet(options, SenderOptions::new);
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(this.options.connectTimeout, ChronoUnit.MILLIS))
                .build();
    }

    @Override
    public <T, M extends JsonResponseDeserializer<T>> T send(M method) {
        try {
            //TODO: validate method
            final String url = BASE_URL + botToken + "/" + method.getClass().getSimpleName();
            final String body = mapper.writeValueAsString(method);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.of(options.requestTimeout, ChronoUnit.MILLIS))
                    .POST(BodyPublishers.ofString(body))
                    .uri(URI.create(url))
                    .header(CHARSET_HEADER, StandardCharsets.UTF_8.name())
                    .header(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE_VALUE)
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() == 200) {
                return method.deserializeResponse(mapper, response.body());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            //log
        } catch (IOException e) {
            e.printStackTrace();
            //log
        }

        return null;
    }

    @Getter
    @Setter
    public static class SenderOptions {
        private int connectTimeout = 30 * 1000;
        private int requestTimeout = 30 * 1000;
    }
}

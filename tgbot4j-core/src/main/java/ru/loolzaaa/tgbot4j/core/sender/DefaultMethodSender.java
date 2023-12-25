package ru.loolzaaa.tgbot4j.core.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.JsonResponseDeserializer;
import ru.loolzaaa.tgbot4j.core.api.methods.GetUpdates;

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

public class DefaultMethodSender implements MethodSender {

    private static final Logger log = LoggerFactory.getLogger(DefaultMethodSender.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient httpClient;

    private final String botToken;

    private final SenderOptions options;

    public DefaultMethodSender(String botToken, SenderOptions options) {
        this.botToken = botToken;
        this.options = Objects.requireNonNullElseGet(options, SenderOptions::new);
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(this.options.connectTimeout, ChronoUnit.MILLIS))
                .build();
        log.info("Default telegram sender created with next options: {}", options);
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
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return method.deserializeResponse(mapper, response.body());
            }
            log.warn("{} error status code: {}. Response content: {}",
                    GetUpdates.class.getSimpleName(), statusCode, response.body());
        } catch (InterruptedException e) {
            log.info("{} request interrupted with message: {}", GetUpdates.class.getSimpleName(), e.getLocalizedMessage());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        //TODO: should throw exception? Need always return result!
        return null;
    }

    @Getter
    @Setter
    @ToString
    public static class SenderOptions {
        private int connectTimeout = 30 * 1000;
        private int requestTimeout = 30 * 1000;
    }
}

package ru.loolzaaa.tgbot4j.core.sender;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
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
import java.util.*;

import static ru.loolzaaa.tgbot4j.core.Constants.*;

public class SyncMethodSender implements MethodSender {

    private static final Logger log = LoggerFactory.getLogger(SyncMethodSender.class);

    private final static char[] MULTIPART_CHARS =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    .toCharArray();

    private final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpClient httpClient;

    private final String botToken;

    private final SenderOptions options;

    public SyncMethodSender(String botToken, SenderOptions options) {
        this.botToken = botToken;
        this.options = Objects.requireNonNullElseGet(options, SenderOptions::new);
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(this.options.connectTimeout, ChronoUnit.MILLIS))
                .build();
        log.info("Default telegram sender created with next options: {}", options);
    }

    @Override
    public <T, M extends TelegramMethod<T>> T send(M method) {
        try {
            method.validate();
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

    public <T, M extends TelegramMultipartMethod<T>> T sendMultipart(M method) {
        try {
            method.validate();
            final String url = BASE_URL + botToken + "/" + method.getClass().getSimpleName();

            final String boundary = generateBoundary();

            List<byte[]> byteArrays = new ArrayList<>();

            Map<String, byte[]> parts = method.getParts();

            // Returning Values from Forms: multipart/form-data
            // https://datatracker.ietf.org/doc/html/rfc7578

            /*
            --AaB03x
            content-disposition: form-data; name="_charset_"

            UTF-8
             */


            // content-type: text/plain;charset=UTF-8 <--- for text fields
            parts.forEach((s, bytes) -> {
                byteArrays.add(String.format("\r\n--%s\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: application/octet-stream\r\n\r\n"));
                byteArrays.add(/* field/file bytes */);
            });
            byteArrays.add(String.format("\r\n--%s--", boundary).getBytes(StandardCharsets.UTF_8));


            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.of(options.requestTimeout, ChronoUnit.MILLIS))
                    .POST(BodyPublishers.ofByteArrays(byteArrays))
                    .uri(URI.create(url))
                    .header(CHARSET_HEADER, StandardCharsets.UTF_8.name())
                    .header(CONTENT_TYPE_HEADER, MULTIPART_CONTENT_TYPE_VALUE)
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

    private String generateBoundary() {
        final StringBuilder buffer = new StringBuilder();
        final Random rand = new Random();
        final int count = rand.nextInt(11) + 30; // a random size from 30 to 40
        for (int i = 0; i < count; i++) {
            buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        return buffer.toString();
    }
}

package ru.loolzaaa.tgbot4j.bot.sender;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.TelegramMethod;
import ru.loolzaaa.tgbot4j.core.api.TelegramMultipartMethod;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;
import ru.loolzaaa.tgbot4j.core.exception.ApiRequestException;
import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;
import ru.loolzaaa.tgbot4j.util.MultipartUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;
import static ru.loolzaaa.tgbot4j.core.Constants.*;

public class DefaultMethodSender implements MethodSender {

    private static final Logger log = LoggerFactory.getLogger(DefaultMethodSender.class);

    private final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpClient httpClient;

    private final ExecutorService executorService;

    private final String botToken;

    private final SenderOptions options;

    public DefaultMethodSender(String botToken, SenderOptions options) {
        this.botToken = botToken;
        this.options = Objects.requireNonNullElseGet(options, SenderOptions::new);
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(this.options.connectTimeout, ChronoUnit.MILLIS))
                .build();
        this.executorService = Executors.newFixedThreadPool(this.options.maxThreads);
        log.info("Default telegram sender created with next options: {}", options);
    }

    @Override
    public <T, M extends TelegramMethod<T>> T send(M method) {
        try {
            method.validate();
            final String url = BASE_URL + botToken + "/" + method.getClass().getSimpleName();

            String contentType;
            BodyPublisher body;
            if (method instanceof TelegramMultipartMethod<?>) {
                final String boundary = MultipartUtils.generateBoundary();
                contentType = MULTIPART_CONTENT_TYPE_VALUE + "; boundary=" + boundary;
                body = prepareMultipartBody((TelegramMultipartMethod<?>) method, boundary);
            } else {
                contentType = JSON_CONTENT_TYPE_VALUE;
                body = prepareStringBody(method);
            }

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .timeout(Duration.of(options.requestTimeout, ChronoUnit.MILLIS))
                    .POST(body)
                    .uri(URI.create(url))
                    .header(CHARSET_HEADER, StandardCharsets.UTF_8.name())
                    .header(CONTENT_TYPE_HEADER, contentType)
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString(StandardCharsets.UTF_8));
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                log.error("{} error status code: {}. Response content: {}",
                        method.getClass().getSimpleName(), statusCode, response.body());
                throw new ApiRequestException(format("%s request error. Status: %d. Response body: %s",
                        method.getClass().getSimpleName(), statusCode, response.body()));
            }
            return method.deserializeResponse(mapper, response.body());
        } catch (InterruptedException e) {
            log.info("{} request interrupted with message: {}", method.getClass().getSimpleName(), e.getLocalizedMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T, M extends TelegramMethod<T>> CompletableFuture<T> sendAsync(M method) {
        CompletableFuture<T> futureResult = new CompletableFuture<>();
        executorService.submit(() -> {
            try {
                futureResult.complete(send(method));
            } catch (Exception e) {
                futureResult.completeExceptionally(e);
            }
        });
        return futureResult;
    }

    private <M extends TelegramMethod<?>> BodyPublisher prepareStringBody(M method) throws IOException {
        final String stringBody = mapper.writeValueAsString(method);
        return BodyPublishers.ofString(stringBody);
    }

    private <M extends TelegramMultipartMethod<?>> BodyPublisher prepareMultipartBody(M method, String boundary) throws IOException {
        List<MultipartBodyPart> parts = method.getBodyParts(mapper);
        List<byte[]> multipartBody = MultipartUtils.createMultipartBody(boundary, parts);
        return BodyPublishers.ofByteArrays(multipartBody);
    }

    @Getter
    @Setter
    @ToString
    public static class SenderOptions {
        private int connectTimeout = 30 * 1000;
        private int requestTimeout = 30 * 1000;
        private int maxThreads = 1;
    }
}

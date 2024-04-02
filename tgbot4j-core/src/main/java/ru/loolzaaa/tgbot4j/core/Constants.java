package ru.loolzaaa.tgbot4j.core;

import java.util.List;

/**
 * Common library constants
 */

public class Constants {
    public static final String BASE_URL = "https://api.telegram.org/bot";
    public static final String SECRET_TOKEN_HEADER = "X-Telegram-Bot-Api-Secret-Token";

    public static final List<Integer> webhookSupportedPorts = List.of(443, 80, 88, 8443);

    public static final String CHARSET_HEADER = "Charset";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    public static final String TEXT_CONTENT_TYPE_VALUE = "text/plain";
    public static final String JSON_CONTENT_TYPE_VALUE = "application/json";
    public static final String MULTIPART_CONTENT_TYPE_VALUE = "multipart/form-data";
    public static final String MULTIPART_BINARY_CONTENT_TYPE = "application/octet-stream";

    public static final String HTTP_METHOD_POST = "POST";

    public static final int HTTP_CODE_OK = 200;
    public static final int HTTP_CODE_UNAUTHORIZED = 401;
    public static final int HTTP_CODE_METHOD_NOT_ALLOWED = 405;

    private Constants() {
    }
}

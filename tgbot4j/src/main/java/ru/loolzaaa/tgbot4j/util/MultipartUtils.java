package ru.loolzaaa.tgbot4j.util;

import ru.loolzaaa.tgbot4j.core.pojo.MultipartBodyPart;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static ru.loolzaaa.tgbot4j.core.Constants.*;

/**
 * Utilizing creation process for multipart body.
 * <p>
 * Returning Values from Forms: multipart/form-data
 * <p>
 * Details: <a href="https://datatracker.ietf.org/doc/html/rfc7578">https://datatracker.ietf.org/doc/html/rfc7578</a>
 */
public final class MultipartUtils {

    private static final char[] MULTIPART_CHARS =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    .toCharArray();

    private static final String NEW_LINE = "\r\n";
    private static final String BOUNDARY_PATTERN = NEW_LINE + "--%s";
    private static final String CONTENT_DISPOSITION_PATTERN = CONTENT_DISPOSITION_HEADER + ": form-data; name=\"%s\"";
    private static final String CONTENT_DISPOSITION_FILE_PATTERN = CONTENT_DISPOSITION_PATTERN + "; filename=\"%s\"";
    private static final String CONTENT_TYPE_PATTERN = CONTENT_TYPE_HEADER + ": %s";
    private static final String CONTENT_TYPE_WITH_CHARSET_PATTERN = CONTENT_TYPE_PATTERN + ";charset=%s";
    private static final String END_BOUNDARY_PATTERN = BOUNDARY_PATTERN + "--";

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final SecureRandom rand = new SecureRandom();

    /**
     * Creating multipart body.
     * <p>
     * Creation process based on parameters of multipart type.
     *
     * @param boundary generated boundary
     * @param parts    body parts
     * @return list of byte arrays with multipart parts
     * @see MultipartBodyPart
     */
    public static List<byte[]> createMultipartBody(String boundary, List<MultipartBodyPart> parts) {
        List<byte[]> byteArrays = new ArrayList<>();

        // Set default charset for every part
        String defaultCharset = format(BOUNDARY_PATTERN, boundary) +
                                NEW_LINE +
                                format(CONTENT_DISPOSITION_PATTERN, "_charset_") +
                                NEW_LINE + NEW_LINE +
                                DEFAULT_CHARSET;
        byteArrays.add(defaultCharset.getBytes(StandardCharsets.UTF_8));

        // Add main parts
        for (MultipartBodyPart part : parts) {
            StringBuilder partBuilder = new StringBuilder(format(BOUNDARY_PATTERN, boundary) + NEW_LINE);
            if (part.isBinary()) {
                partBuilder
                        .append(format(CONTENT_DISPOSITION_FILE_PATTERN, part.name(), part.name()))
                        .append(NEW_LINE)
                        .append(format(CONTENT_TYPE_PATTERN, MULTIPART_BINARY_CONTENT_TYPE))
                        .append(NEW_LINE).append(NEW_LINE);
            } else {
                partBuilder
                        .append(format(CONTENT_DISPOSITION_PATTERN, part.name()))
                        .append(NEW_LINE)
                        .append(format(CONTENT_TYPE_WITH_CHARSET_PATTERN, TEXT_CONTENT_TYPE_VALUE, DEFAULT_CHARSET))
                        .append(NEW_LINE).append(NEW_LINE);
            }
            byteArrays.add(partBuilder.toString().getBytes(StandardCharsets.UTF_8));
            byteArrays.add(part.value());
        }

        // Add end body boundary
        byteArrays.add(format(END_BOUNDARY_PATTERN, boundary).getBytes(StandardCharsets.UTF_8));

        return byteArrays;
    }

    /**
     * Generating multipart boundary.
     *
     * @return generated boundary
     */
    public static String generateBoundary() {
        final StringBuilder buffer = new StringBuilder();
        final int count = rand.nextInt(11) + 30; // a random size from 30 to 40
        for (int i = 0; i < count; i++) {
            buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        return buffer.toString();
    }
}

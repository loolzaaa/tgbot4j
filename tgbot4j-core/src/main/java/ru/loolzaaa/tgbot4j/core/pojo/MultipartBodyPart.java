package ru.loolzaaa.tgbot4j.core.pojo;

/**
 * This record is one body part Of a multipart request
 * when serializing an API method.
 *
 * @param name     body part name
 * @param value    body part value
 * @param isBinary binary body part or not
 */

public record MultipartBodyPart(String name, byte[] value, boolean isBinary) {
}

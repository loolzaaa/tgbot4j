package ru.loolzaaa.tgbot4j.core.pojo;

public record MultipartBodyPart(String name, byte[] value, boolean isBinary) {
}

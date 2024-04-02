package ru.loolzaaa.tgbot4j.core.pojo;

import java.util.Arrays;
import java.util.Objects;

/**
 * This record is one body part Of a multipart request
 * when serializing an API method.
 *
 * @param name     body part name
 * @param value    body part value
 * @param isBinary binary body part or not
 */

public record MultipartBodyPart(String name, byte[] value, boolean isBinary) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipartBodyPart that = (MultipartBodyPart) o;
        return isBinary == that.isBinary && Objects.equals(name, that.name) && Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, isBinary);
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "MultipartBodyPart{" +
               "name='" + name + '\'' +
               ", value=" + Arrays.toString(value) +
               ", isBinary=" + isBinary +
               '}';
    }
}

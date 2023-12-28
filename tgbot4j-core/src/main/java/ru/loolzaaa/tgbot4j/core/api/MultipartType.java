package ru.loolzaaa.tgbot4j.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartType {

    Type value() default Type.TEXT;

    enum Type {
        TEXT, JSON, BINARY
    }
}

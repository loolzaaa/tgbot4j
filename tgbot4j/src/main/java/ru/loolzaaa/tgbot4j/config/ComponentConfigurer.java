package ru.loolzaaa.tgbot4j.config;

@FunctionalInterface
public interface ComponentConfigurer<T> {
    T configure();
}

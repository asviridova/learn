package ru.otus.spring.config;

public interface MessageByLocaleService {

    String getMessage(String id);

    String getMessage(String id, String language);
}

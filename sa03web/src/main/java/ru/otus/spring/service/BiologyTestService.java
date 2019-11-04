package ru.otus.spring.service;

import java.util.Map;

public interface BiologyTestService {
    Map<String, String> getMap();

    String getCurrentQuestion();

    String getCurrentAnswer();

    void checkCurrentAnswer(String answer);
}

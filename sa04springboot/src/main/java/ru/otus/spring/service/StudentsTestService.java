package ru.otus.spring.service;

import java.util.Map;

public interface StudentsTestService {
    String getCurrentQuestion();

    String getCurrentAnswer();

    void checkCurrentAnswer(String answer);

    boolean isFlagTestFinished();

    void setFio(String fio) ;

}

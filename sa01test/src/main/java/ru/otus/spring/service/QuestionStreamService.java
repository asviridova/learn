package ru.otus.spring.service;

public interface QuestionStreamService {
    void writeQuestion(String question);

    String readAnswer();

    void writeResult(String result);

    void writeAgreement();

    String readFIO();
}

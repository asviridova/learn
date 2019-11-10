package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.Map;

public interface QuestionDao {
    Map<String, String> getMap();

    Question getQuestionByNumber(int number);

}

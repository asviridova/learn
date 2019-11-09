package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.Locale;
import java.util.Map;

public interface QuestionDao {
    Map<String, String> getMap();

    Question getQuestionByNumber(int number);

    Map<String, String> getMap(String language);

    Question getQuestionByNumber(int number,String language);
}

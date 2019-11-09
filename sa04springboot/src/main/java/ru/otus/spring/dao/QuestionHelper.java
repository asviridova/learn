package ru.otus.spring.dao;

import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.domain.Question;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class QuestionHelper {


    private Map<String, String> mapQuestionToAnswer = new HashMap<String, String>();
    private Map<Integer, Question> mapNumberToQuestion = new HashMap<Integer, Question>();

}

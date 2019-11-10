package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.Map;


//@Service("SessionQuestionService")
//@Slf4j
public class BiologyTestServiceImpl implements BiologyTestService {


    private Integer currentQuestion = 0;
    private String currentAnswer = null;

    private Integer success = 0;
    private Integer error = 0;

    private QuestionDao questionDao;

    @Autowired
    public BiologyTestServiceImpl(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    public static final String RESULT_PREFIX = "Тестирование закончено:";

    public Map<String, String> getMap(){
        return questionDao.getMap();
    }

    public String getCurrentQuestion(){
        Question question = questionDao.getQuestionByNumber(++currentQuestion);
        if(question==null){
            String currentTestResult = RESULT_PREFIX+" Правильных ответов="+success+", ошибок="+error;
            currentAnswer = null;
            currentQuestion = 0;
            success = 0;
            error = 0;
            return currentTestResult;
        }
        else{
            currentAnswer = question.getAnswer();
            //System.out.println("currentAnswer="+currentAnswer);
            return question.getQuestion();
        }
    }

    public String getCurrentAnswer(){
        return currentAnswer;
    }

    public void checkCurrentAnswer(String answer){
        if(!StringUtils.isEmpty(answer)){
            if(currentAnswer==null){
                return;
            }
            if(answer.equalsIgnoreCase(currentAnswer)){
                success++;
            }
            else{
                error++;
            }
        }
    }

    }

package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;



public class StudentsTestServiceImpl implements StudentsTestService {


    private int currentQuestion = 0;
    private String currentAnswer = null;

    private int success = 0;
    private int error = 0;

    private final QuestionDao questionDao;
    private boolean flagTestFinished = false;

    @Autowired
    public StudentsTestServiceImpl(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    public String getCurrentQuestion(){
        Question question = questionDao.getQuestionByNumber(++currentQuestion);
        if(question==null){
            String currentTestResult = ", тестирование закончено: правильных ответов="+success+", ошибок="+error;
            currentAnswer = null;
            currentQuestion = 0;
            success = 0;
            error = 0;
            flagTestFinished = true;
            return currentTestResult;
        }
        else{
            currentAnswer = question.getAnswer();
            flagTestFinished = false;
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

    @Override
    public boolean isFlagTestFinished(){
        return flagTestFinished;
    }


    }

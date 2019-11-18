package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.spring.config.AppConfig;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.ResourceBundle;


@Service
public class StudentsTestServiceImpl implements StudentsTestService {


    private int currentQuestion = 0;
    private String currentAnswer = null;

    private int success = 0;
    private int error = 0;

    private final QuestionDao questionDao;
    private boolean flagTestFinished = false;

    private final AppConfig appConfig;
    private final LocalizationService localizationService;

    private String fio;

    @Autowired
    public StudentsTestServiceImpl(QuestionDao questionDao, AppConfig appConfig, LocalizationService localizationService){
        this.questionDao = questionDao;
        this.appConfig = appConfig;
        this.localizationService = localizationService;

    }

    public String getCurrentQuestion(){
        Question question = questionDao.getQuestionByNumber(++currentQuestion);
        if(question==null){
            return fio+getResultInfo();
        }
        else{
            currentAnswer = question.getAnswer();
            flagTestFinished = false;
            return question.getQuestion();
        }
    }

    private String getResultInfo(){
        int successCountToPass =  appConfig.getSuccessCount();
        String pattern = localizationService.getLocalizedMessage("result.message");
        String currentTestResult = String.format(pattern, ""+success, ""+error);
        if(success < successCountToPass){
            String tail = localizationService.getLocalizedMessage("result.message.tail.error");
            currentTestResult = currentTestResult+tail;
        }
        else{
            String tail = localizationService.getLocalizedMessage("result.message.tail.success");
            currentTestResult = currentTestResult+tail;
        }

        currentAnswer = null;
        currentQuestion = 0;
        success = 0;
        error = 0;
        flagTestFinished = true;
        return currentTestResult;
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


    public void setFio(String fio) {
        this.fio = fio;
    }



    }

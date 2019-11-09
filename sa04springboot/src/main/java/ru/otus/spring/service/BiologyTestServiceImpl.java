package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.spring.config.MessageByLocaleService;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoImpl;
import ru.otus.spring.domain.Question;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("SessionQuestionService")
@Slf4j
public class BiologyTestServiceImpl implements BiologyTestService {


    private Integer currentQuestion = 0;
    private String currentAnswer = null;

    private Integer success = 0;
    private Integer error = 0;

    private QuestionDao questionDao;
    private MessageByLocaleService messageByLocaleService;
    private String language;

    @Autowired
    public BiologyTestServiceImpl(QuestionDao questionDao, MessageByLocaleService messageByLocaleService){
        this.questionDao = questionDao;
        this.messageByLocaleService = messageByLocaleService;
    }


    @Override
    public void setLanguage(String language){
        this.language = language;
    }

    public Map<String, String> getMap(){
        return questionDao.getMap(language==null? QuestionDaoImpl.DEFAULT_LANGUAGE: language);
    }

    public String getCurrentQuestion(){
        Question question = questionDao.getQuestionByNumber(++currentQuestion, language==null? QuestionDaoImpl.DEFAULT_LANGUAGE: language);
        if(question==null){
            String currentTestResult = getFinalMessage();
            currentAnswer = null;
            currentQuestion = 0;
            success = 0;
            error = 0;
            return currentTestResult;
        }
        else{
            currentAnswer = question.getAnswer();
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

    private String getFinalMessage(){
        String resMsg = messageByLocaleService.getMessage("result.message", language==null? QuestionDaoImpl.DEFAULT_LANGUAGE: language );
        String currentTestResult = String.format(resMsg, ""+success, ""+error);
        return currentTestResult;
    }
}

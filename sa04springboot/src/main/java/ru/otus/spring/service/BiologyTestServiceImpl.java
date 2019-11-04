package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;


//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("SessionQuestionService")
@Slf4j
public class BiologyTestServiceImpl implements BiologyTestService {

    //@Value("classpath:data/questions.csv")
    //Resource resourceFile;

    Map<String, String> mapQuestionToAnswer = new HashMap<String, String>();
    Map<Integer, Map<String, String>> mapNumberToMap = new HashMap<Integer, Map<String, String>>();

    Integer currentQuestion = 0;
    String currentAnswer = null;

    Integer success = 0;
    Integer error = 0;


    private String fileContent;
    public BiologyTestServiceImpl(){
        Resource resourceFile = loadQuestions();
        fileContent = ResourceReader.asString(resourceFile);
        parse(fileContent);
    }

    public Resource loadQuestions() {
        return new ClassPathResource("data/questions.csv");
    }

    private void parse(String fileContent){
        //log.info("fileContent="+fileContent);
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>1){
                    mapQuestionToAnswer.put(elements[0], StringUtils.trimWhitespace(elements[1]));
                    Map<String, String> mapInnerQuestionToAnswer = new HashMap<String, String>();
                    mapInnerQuestionToAnswer.put(elements[0], StringUtils.trimWhitespace(elements[1]));
                    mapNumberToMap.put(++i, mapInnerQuestionToAnswer);
                }
            }

        }
    }

    public Map<String, String> getMap(){
        return mapQuestionToAnswer;
    }

    public String getCurrentQuestion(){
        Map<String, String> mapQ =  mapNumberToMap.get(++currentQuestion);
        if(mapQ==null){
            currentAnswer = "Тестирование закончено: Правильных ответов="+success+", ошибок="+error;
            currentQuestion = 0;
            success = 0;
            error = 0;
            return currentAnswer;
        }
        else{
            String key = mapQ.keySet().iterator().next();
            currentAnswer = mapQ.get(key);
            return key; //здесь мапа с одним значением
        }
    }

    public String getCurrentAnswer(){
        return currentAnswer;
    }

    public void checkCurrentAnswer(String answer){
        if(!StringUtils.isEmpty(answer)){
            if(answer.equalsIgnoreCase(currentAnswer)){
                success++;
            }
            else{
                error++;
            }
        }
    }

    }

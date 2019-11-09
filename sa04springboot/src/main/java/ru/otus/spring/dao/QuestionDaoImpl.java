package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import ru.otus.spring.domain.Question;

import java.util.*;

public class QuestionDaoImpl implements QuestionDao {

    private Map<String, QuestionHelper> mapLangToQuestionHelper = new HashMap<String, QuestionHelper>();
    public static final String DEFAULT_LANGUAGE = "ru";
    //@Value("classpath:data/questions.csv")
    //Resource resourceFile;

    public QuestionDaoImpl(){
        List<String> languages = new ArrayList<String>();
        languages.add("ru");
        languages.add("en");
        for(String language: languages){
            Locale locale = new Locale(language);
            ResourceBundle messages = ResourceBundle.getBundle("messages", locale);

            String fileName = messages.getString("file.data");
            Resource resourceFile = loadQuestions(fileName);
            String fileContent = ResourceReader.asString(resourceFile);
            parse(fileContent, language);

        }

    }

    public Resource loadQuestions(String fileName) {
        return new ClassPathResource(fileName);
    }

    private void parse(String fileContent, String language){
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            QuestionHelper questionHelper = new QuestionHelper();
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>1){
                    String questionElement = elements[0];
                    String answerElement = StringUtils.trimWhitespace(elements[1]);
                    Question question = new Question(questionElement, answerElement);
                    questionHelper.getMapQuestionToAnswer().put(questionElement, answerElement);
                    questionHelper.getMapNumberToQuestion().put(++i, question);
                }
            }
            this.mapLangToQuestionHelper.put(language, questionHelper);
        }
    }

    @Override
    public Map<String, String> getMap(){
        return mapLangToQuestionHelper.get(DEFAULT_LANGUAGE).getMapQuestionToAnswer();
    }


    @Override
    public Question getQuestionByNumber(int number){
        return mapLangToQuestionHelper.get(DEFAULT_LANGUAGE).getMapNumberToQuestion().get(number);
    }

    @Override
    public Map<String, String> getMap(String language){
        return mapLangToQuestionHelper.get(language).getMapQuestionToAnswer();
    }


    @Override
    public Question getQuestionByNumber(int number, String language){
        return mapLangToQuestionHelper.get(language).getMapNumberToQuestion().get(number);
    }

}

package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDaoImpl implements QuestionDao {

    private List<Question> listOfQuestions = new ArrayList<Question>();

    private final String resourceFileName;

    private String fileContent;
    public QuestionDaoImpl(String resourceFileName){
        this.resourceFileName = resourceFileName;
        Resource resourceFile = loadQuestions();
        fileContent = ResourceReader.asString(resourceFile);
        parse(fileContent);
    }

    private Resource loadQuestions() {
        return new ClassPathResource(resourceFileName);
    }

    private void parse(String fileContent){
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>1){
                    String questionElement = elements[0];
                    String answerElement = StringUtils.trimWhitespace(elements[1]);
                    Question question = new Question(questionElement, answerElement);
                    listOfQuestions.add(question);
                }
            }

        }
    }


    @Override
    public Question getQuestionByNumber(int number){
        if(listOfQuestions.size() >= number) {
            return listOfQuestions.get(number - 1);
        }
        return null;
    }

}

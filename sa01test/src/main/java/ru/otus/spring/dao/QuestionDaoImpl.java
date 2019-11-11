package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import ru.otus.spring.domain.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionDaoImpl implements QuestionDao {

    private Map<String, String> mapQuestionToAnswer = new HashMap<String, String>();
    private Map<Integer, Question> mapNumberToQuestion = new HashMap<Integer, Question>();

    //@Value("classpath:data/questions.csv")
    //Resource resourceFile;

    private final String resourceFileName;

    private String fileContent;
    public QuestionDaoImpl(String resourceFileName){
        this.resourceFileName = resourceFileName;
        Resource resourceFile = loadQuestions();
        fileContent = ResourceReader.asString(resourceFile);
        parse(fileContent);
    }

    public Resource loadQuestions() {
        //return new ClassPathResource("data/questions.csv");
        return new ClassPathResource(resourceFileName);
    }

    private void parse(String fileContent){
        //log.info("fileContent="+fileContent);
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>1){
                    String questionElement = elements[0];
                    String answerElement = StringUtils.trimWhitespace(elements[1]);
                    Question question = new Question(questionElement, answerElement);
                    mapQuestionToAnswer.put(questionElement, answerElement);
                    mapNumberToQuestion.put(++i, question);
                }
            }

        }
    }



    @Override
    public Question getQuestionByNumber(int number){
        return mapNumberToQuestion.get(number);
    }

}

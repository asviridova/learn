package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoImpl;
import ru.otus.spring.domain.Question;

@Configuration
public class DaoConfig {

    @Bean
    public PersonDao personDao() {
        return new PersonDaoSimple();
    }

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoImpl();
    }

}

package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.BiologyTestService;
import ru.otus.spring.service.BiologyTestServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public BiologyTestService biologyTestService(QuestionDao questionDao) {
        return new BiologyTestServiceImpl(questionDao);
    }

}

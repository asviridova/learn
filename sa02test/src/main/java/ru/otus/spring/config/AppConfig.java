package ru.otus.spring.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoImpl;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
public class AppConfig {

    @Value("${student.test.successcount}")
    private int successCount;

    @Value("${student.test.locale}")
    private String localeLanguage;

    public static String DEFAULT_LOCALE_LANG = "ru";
    private Locale locale=null;

    @Bean
    public QuestionDao questionDao(@Value("${app.resource.filename}") String resourceFileName) {
        if(StringUtils.isEmpty(localeLanguage)){
            localeLanguage = DEFAULT_LOCALE_LANG;
        }
        locale = new Locale(localeLanguage);
        return new QuestionDaoImpl(resourceFileName, locale);
    }


}

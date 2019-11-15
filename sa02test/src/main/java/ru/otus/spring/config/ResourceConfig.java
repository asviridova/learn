package ru.otus.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;


@Configuration
public class ResourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        //messageSource.setCacheSeconds(3600); //refresh cache once per hour
        messageSource.setDefaultEncoding("UTF-8"); //
        return messageSource;
    }


}

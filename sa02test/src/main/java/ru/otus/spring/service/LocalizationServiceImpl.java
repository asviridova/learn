package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;


@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final AppConfig appConfig;
    private final MessageSource messageSource;

    @Autowired
    public LocalizationServiceImpl(AppConfig appConfig, MessageSource messageSource){
        this.appConfig = appConfig;
        this.messageSource =messageSource;
    }


    public String getLocalizedMessage(String key){
        return messageSource.getMessage(key, null, appConfig.getLocale());
    }
}

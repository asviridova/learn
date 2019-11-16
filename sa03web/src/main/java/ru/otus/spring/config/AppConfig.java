package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Component
@ConfigurationProperties("application")
@Getter
@Setter
public class AppConfig {

    public static final String DEFAULT_LOCALE_LANG = "ru";
    public static final String DEFAULT_RESOURCE_FILE_PATTERN = "data/questions_%s.csv";

    private int successCount;

    private String localeLanguage;

    private String resourceFileName;

    private Locale locale;

    private String fileNamePattern;

    public AppConfig(){
        if(StringUtils.isEmpty(localeLanguage)){
            localeLanguage = DEFAULT_LOCALE_LANG;
        }
        locale = new Locale(localeLanguage);
        if(StringUtils.isEmpty(fileNamePattern)){
            fileNamePattern = DEFAULT_RESOURCE_FILE_PATTERN;
        }
        resourceFileName = String.format(fileNamePattern, localeLanguage);

    }



}

package ru.otus.spring.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;

@Component
@PropertySource("classpath:application.properties")
@Getter
public class AppConfig {

    public static final String DEFAULT_LOCALE_LANG = "ru";
    public static final String DEFAULT_RESOURCE_FILE_PATTERN = "data/questions_%s.csv";

    private int successCount;

    private String localeLanguage;

    private String resourceFileName;

    private Locale locale;

    public AppConfig(@Value("${student.test.successcount}") int successCount, @Value("${student.test.locale}") String localeLanguage, @Value("${student.filename.pattern}") String fileNamePattern){
        this.successCount = successCount;
        if(StringUtils.isEmpty(localeLanguage)){
            localeLanguage = DEFAULT_LOCALE_LANG;
        }
        this.localeLanguage = localeLanguage;
        locale = new Locale(localeLanguage);
        if(StringUtils.isEmpty(fileNamePattern)){
            fileNamePattern = DEFAULT_RESOURCE_FILE_PATTERN;
        }
        resourceFileName = String.format(fileNamePattern, localeLanguage);

    }


}

package ru.otus.spring.service;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppConfig;

import java.util.ResourceBundle;


@Getter
@Service
public class StudentAccountServiceImpl implements StudentAccountService {

    private final WriteReadService writeReadService;
    private final LocalizationService localizationService;


    public StudentAccountServiceImpl(WriteReadService writeReadService, LocalizationService localizationService ){
        this.writeReadService = writeReadService;
        this.localizationService = localizationService;
    }

    public void writeAgreement(){
        String greeting = localizationService.getLocalizedMessage("greeting");
        writeReadService.writeMessage(greeting);
    }

    public String getAgreement(){
        String greeting = localizationService.getLocalizedMessage("greeting");
        return greeting;
    }

    public String readFIO(){
        return writeReadService.readMessage();
    }

}

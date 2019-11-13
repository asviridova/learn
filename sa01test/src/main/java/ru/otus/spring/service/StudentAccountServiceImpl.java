package ru.otus.spring.service;

import lombok.Getter;


@Getter
public class StudentAccountServiceImpl implements StudentAccountService {

    private final WriteReadService writeReadService;
    public StudentAccountServiceImpl(WriteReadService writeReadService){
        this.writeReadService = writeReadService;
    }

    public void writeAgreement(){
        writeReadService.writeMessage("Добрый день, введите пожалуйста ФИО:");
    }

    public String readFIO(){
        return writeReadService.readMessage();
    }


}

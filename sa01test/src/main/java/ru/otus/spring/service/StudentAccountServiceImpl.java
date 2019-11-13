package ru.otus.spring.service;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class StudentAccountServiceImpl implements StudentAccountService {

    final WriteReadService writeReadService;
    public StudentAccountServiceImpl(WriteReadService writeReadService){
        this.writeReadService = writeReadService;
    }

    private String fio = null;
    public void writeAgreement(){
        writeReadService.writeMessage("Добрый день, введите пожалуйста ФИО:");
    }

    public String readFIO(){
        return writeReadService.readMessage();
    }


}

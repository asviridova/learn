package ru.otus.spring.service;

import lombok.Getter;

import java.util.Scanner;

@Getter
public class StudentAccountServiceImpl implements StudentAccountService {

    private String fio = null;
    public void writeAgreement(){
        System.out.println("Добрый день, введите пожалуйста ФИО:");
    }

    public String readFIO(){
        Scanner scanner = new Scanner(System.in);
        String fio = scanner.next();
        return fio;
    }


}

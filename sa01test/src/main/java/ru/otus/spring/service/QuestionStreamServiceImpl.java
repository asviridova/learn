package ru.otus.spring.service;

import java.util.Scanner;

public class QuestionStreamServiceImpl implements QuestionStreamService {
    Scanner scanner = new Scanner(System.in);
    private String fio = null;

    public void writeQuestion(String question){
        System.out.println(question);
    }

    public String readAnswer(){
        String answer = scanner.next();
        return answer;
    }

    public void writeResult(String result){
        System.out.println(result);
    }

    public void writeAgreement(){
        System.out.println("Добрый день, введите пожалуйста ФИО:");
    }

    public String readFIO(){
        String fio = scanner.next();
        return fio;
    }

}

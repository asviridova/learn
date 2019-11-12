package ru.otus.spring.service;

import java.util.Scanner;

public class QuestionStreamServiceImpl implements QuestionStreamService {
    Scanner scanner = new Scanner(System.in);
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
}

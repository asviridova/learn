package ru.otus.spring.service;

import java.util.Scanner;

public class StudentsTestRunnerServiceImpl implements StudentsTestRunnerService{

    private final StudentsTestService studentsTestService;

    public StudentsTestRunnerServiceImpl(StudentsTestService studentsTestService){
        this.studentsTestService = studentsTestService;
    }

    public void run(String[] args){
        System.out.println("Добрый день, введите пожалуйста ФИО:");

        Scanner scanner = new Scanner(System.in);
        String fio = scanner.next();

        String question =  studentsTestService.getCurrentQuestion();
        System.out.println(question);
        while (question!=null && !studentsTestService.isFlagTestFinished()){
            String answer = scanner.next();
            studentsTestService.checkCurrentAnswer(answer);
            question =  studentsTestService.getCurrentQuestion();
            if(studentsTestService.isFlagTestFinished()) {
                System.out.println(fio+question);
            }
            else{
                System.out.println(question);
            }
        }


    }

}

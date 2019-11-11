package ru.otus.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.StudentsTestRunnerService;
import ru.otus.spring.service.StudentsTestService;
import ru.otus.spring.service.StudentsTestServiceImpl;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        StudentsTestRunnerService studentsTestRunnerService = context.getBean(StudentsTestRunnerService.class);
        studentsTestRunnerService.run(args);


    }
}

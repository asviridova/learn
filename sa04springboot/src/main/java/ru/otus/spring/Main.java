package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.StudentsTestRunnerService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);

        StudentsTestRunnerService studentsTestRunnerService = context.getBean(StudentsTestRunnerService.class);
        studentsTestRunnerService.run(args);


    }
}

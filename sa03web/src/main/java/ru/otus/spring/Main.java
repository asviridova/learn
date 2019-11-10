package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.BiologyTestService;
import ru.otus.spring.service.BiologyTestServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);

        String language = "ru";

        if (args.length >0) {
            language = args[0];
        }

        //------
        BiologyTestService biologyService = context.getBean(BiologyTestService.class);
        biologyService.setLanguage(language);
        Scanner scanner = new Scanner(System.in);
        String question = biologyService.getCurrentQuestion();
        System.out.println(question);
        while (question != null && !biologyService.isFlagTestFinished()) {
            String answer = scanner.next();
            biologyService.checkCurrentAnswer(answer);
            question = biologyService.getCurrentQuestion();
            System.out.println(question);
        }
    }
}
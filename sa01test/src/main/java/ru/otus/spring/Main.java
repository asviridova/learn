package ru.otus.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.BiologyTestService;
import ru.otus.spring.service.BiologyTestServiceImpl;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        BiologyTestService biologyService = context.getBean(BiologyTestService.class);

        Scanner scanner = new Scanner(System.in);
        String question =  biologyService.getCurrentQuestion();
        System.out.println(question);
        while (question!=null && !question.startsWith(BiologyTestServiceImpl.RESULT_PREFIX)){
            String answer = scanner.next();
            biologyService.checkCurrentAnswer(answer);
            question =  biologyService.getCurrentQuestion();
            System.out.println(question);
        }


    }
}

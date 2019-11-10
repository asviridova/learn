package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.BiologyTestService;
import ru.otus.spring.service.BiologyTestServiceImpl;
import ru.otus.spring.service.PersonService;

import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());

        Person vasya = service.getByName("Vasya");
        System.out.println("name: " + vasya.getName() + " age: " + vasya.getAge());

        //-----------------
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

package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.BiologyTestService;
import ru.otus.spring.service.BiologyTestServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
public class Main {

    /*public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        BiologyTestService biologyTestService = context.getBean(BiologyTestServiceImpl.class);
        System.out.println("Map="+biologyTestService.getMap());
    }*/

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);

        String language = "en";
        String country = "US";

//        String language = "ru";
//        String country = "RU";

        if (args.length == 2) {
            language = args[0];
            country = args[1];
        }
        Locale locale = new Locale(language/*, country*/);
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        System.out.print(messages.getString("greeting") + " ");
    }
    
}
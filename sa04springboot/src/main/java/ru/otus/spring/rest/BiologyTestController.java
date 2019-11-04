package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.service.BiologyTestService;

@Controller
public class BiologyTestController {
    private final BiologyTestService biologyTestService;

    public BiologyTestController(BiologyTestService biologyTestService){
        this.biologyTestService = biologyTestService;
    }

    @GetMapping("/next")
    public String getNextQuestion() {
        return biologyTestService.getMap().toString();
    }


//    @GetMapping("/")
//    public String greetingPage(Model model) {
//        boolean isFirstGreetingSuccess = prototypeGreetingService1.isFirstGreetingSuccess();
//        model.addAttribute("singletonGreeting", singletonGreetingService.greeting());
//        model.addAttribute("sessionGreeting", sessionGreetingService.greeting());
//        model.addAttribute("requestGreeting", requestGreetingService.greeting());
//        model.addAttribute("prototype1Greeting", prototypeGreetingService1.greeting());
//        model.addAttribute("prototype2Greeting", isFirstGreetingSuccess? prototypeGreetingService2.greeting(): "Пока жду");
//        return "index";
//    }
}

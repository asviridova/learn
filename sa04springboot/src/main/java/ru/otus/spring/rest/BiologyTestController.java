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


}

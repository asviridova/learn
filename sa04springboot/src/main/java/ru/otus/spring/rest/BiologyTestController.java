package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.service.BiologyTestService;

@RestController
public class BiologyTestController {
    private final BiologyTestService biologyTestService;

    @Autowired
    public BiologyTestController(BiologyTestService biologyTestService){
        this.biologyTestService = biologyTestService;
    }

    @GetMapping("/next")
    public String getNextQuestion() {
        return biologyTestService.getCurrentQuestion();
    }


}

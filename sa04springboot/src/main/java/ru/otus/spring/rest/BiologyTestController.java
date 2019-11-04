package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.service.BiologyTestService;

@Controller
public class BiologyTestController {
    private final BiologyTestService biologyTestService;

    @Autowired
    public BiologyTestController(BiologyTestService biologyTestService){
        this.biologyTestService = biologyTestService;
    }


    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("biologyQuestion", biologyTestService.getCurrentQuestion());
        return "index";
    }


    @GetMapping("/shownext")
    public String getNextQuestion(Model model) {
        return "redirect:/";
    }

    @PostMapping("/select")
    public String editBookPage(@RequestParam("point") String point, Model model) {
        if(!StringUtils.isEmpty(point)){
            if(!point.equalsIgnoreCase("next")){
                biologyTestService.checkCurrentAnswer(point);
            }
        }
        return "redirect:/";
    }


}

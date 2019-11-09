package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.BiologyTestService;

@ShellComponent
public class BiologyTestCommands {
    private final BiologyTestService biologyTestService;

    @Autowired
    public BiologyTestCommands(BiologyTestService biologyTestService){
        this.biologyTestService = biologyTestService;
    }

    @ShellMethod(value = "getMap",  key ={ "get-map", "map" })
    public String getMap(){
        return biologyTestService.getMap().toString();

    }

    @ShellMethod(value = "getStart",  key ={ "start" })
    public String getStart(String language){
        biologyTestService.setLanguage(language);
        return biologyTestService.getCurrentQuestion();

    }


    @ShellMethod(value = "getNext",  key ={ "next" })
    public String getNextQuestion(){
        return biologyTestService.getCurrentQuestion();

    }

    @ShellMethod(value = "getCurrentAnswer",  key ={ "info", "h" })
    public String getCurrentAnswer(){
        return biologyTestService.getCurrentAnswer();

    }


    @ShellMethod(value = "checkA",  key ={ "a", "A" })
    public String checkA(){
        biologyTestService.checkCurrentAnswer("a");
        return biologyTestService.getCurrentQuestion();
    }

    @ShellMethod(value = "checkB",  key ={ "b", "B" })
    public String checkB(){
        biologyTestService.checkCurrentAnswer("b");
        return biologyTestService.getCurrentQuestion();
    }

    @ShellMethod(value = "checkC",  key ={ "c", "C" })
    public String checkC(){
        biologyTestService.checkCurrentAnswer("c");
        return biologyTestService.getCurrentQuestion();
    }

}

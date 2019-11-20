package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.service.StudentAccountService;
import ru.otus.spring.service.StudentsTestService;

@ShellComponent
public class BiologyTestCommands {
    private final StudentsTestService studentsTestService;
    private final StudentAccountService studentAccountService;

    private boolean available = false;
    private boolean fioIsDefined = false;

    @Autowired
    public BiologyTestCommands(StudentsTestService studentsTestService, StudentAccountService studentAccountService){
        this.studentsTestService = studentsTestService;
        this.studentAccountService = studentAccountService;
    }


    @ShellMethod(value = "getStart",  key ={ "start" , "START"})
    public String getStart(){
        available = true;
        return  studentAccountService.getAgreement();
    }


    @ShellMethod(value = "fio",  key ={ "fio" })
    @ShellMethodAvailability("fioAvailability")
    public String setFio( String fio){
        fioIsDefined = true;
        studentsTestService.setFio(fio);
        return getNextQuestion();
    }

    public Availability fioAvailability() {
        return available ? Availability.available() : Availability.unavailable("You should put START command first");
    }

    public Availability questionAvailability() {
        return fioIsDefined ? Availability.available() : Availability.unavailable("You should put FIO command first");
    }

    public String getNextQuestion(){
        String question = studentsTestService.getCurrentQuestion();
        return question;

    }


    @ShellMethod(value = "checkAnswer",  key ={ "answer", "#" })
    @ShellMethodAvailability("questionAvailability")
    public String checkAnswer(String answer){
        studentsTestService.checkCurrentAnswer(answer);
        return studentsTestService.getCurrentQuestion();
    }


}

package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.StudentAccountService;
import ru.otus.spring.service.StudentsTestService;

@ShellComponent
public class BiologyTestCommands {
    private final StudentsTestService studentsTestService;
    private final StudentAccountService studentAccountService;

    @Autowired
    public BiologyTestCommands(StudentsTestService studentsTestService, StudentAccountService studentAccountService){
        this.studentsTestService = studentsTestService;
        this.studentAccountService = studentAccountService;
    }


    @ShellMethod(value = "getStart",  key ={ "start" })
    public String getStart(){
        return  studentAccountService.getAgreement();
    }

    @ShellMethod(value = "fio",  key ={ "fio" })
    public String setFio( String fio){
        studentsTestService.setFio(fio);
        return getNextQuestion();
    }


    @ShellMethod(value = "getNext",  key ={ "next" })
    public String getNextQuestion(){
        String question = studentsTestService.getCurrentQuestion();
        return question;

    }

    @ShellMethod(value = "getCurrentAnswer",  key ={ "info", "h" })
    public String getCurrentAnswer(){
        return studentsTestService.getCurrentAnswer();

    }


    @ShellMethod(value = "checkA",  key ={ "a", "A" })
    public String checkA(){
        studentsTestService.checkCurrentAnswer("a");
        return studentsTestService.getCurrentQuestion();
    }

    @ShellMethod(value = "checkB",  key ={ "b", "B" })
    public String checkB(){
        studentsTestService.checkCurrentAnswer("b");
        return studentsTestService.getCurrentQuestion();
    }

    @ShellMethod(value = "checkC",  key ={ "c", "C" })
    public String checkC(){
        studentsTestService.checkCurrentAnswer("c");
        return studentsTestService.getCurrentQuestion();
    }

}

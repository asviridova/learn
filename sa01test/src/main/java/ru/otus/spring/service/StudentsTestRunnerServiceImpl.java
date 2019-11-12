package ru.otus.spring.service;


public class StudentsTestRunnerServiceImpl implements StudentsTestRunnerService{

    private final StudentsTestService studentsTestService;
    private final StudentAccountService studentAccountService;
    private final QuestionStreamService questionStreamService;

    public StudentsTestRunnerServiceImpl(StudentsTestService studentsTestService, StudentAccountService studentAccountService, QuestionStreamService questionStreamService){
        this.studentsTestService = studentsTestService;
        this.studentAccountService = studentAccountService;
        this.questionStreamService = questionStreamService;
    }

    public void run(String[] args){
        studentAccountService.writeAgreement();
        String fio = studentAccountService.readFIO();

        String question =  studentsTestService.getCurrentQuestion();
        questionStreamService.writeQuestion(question);
        while (question!=null && !studentsTestService.isFlagTestFinished()){
            String answer = questionStreamService.readAnswer();
            studentsTestService.checkCurrentAnswer(answer);
            question =  studentsTestService.getCurrentQuestion();
            if(studentsTestService.isFlagTestFinished()) {
                questionStreamService.writeResult(fio+question);
            }
            else{
                questionStreamService.writeQuestion(question);
            }
        }


    }

}

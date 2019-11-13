package ru.otus.spring.service;


public class StudentsTestRunnerServiceImpl implements StudentsTestRunnerService{

    private final StudentsTestService studentsTestService;
    private final QuestionStreamService questionStreamService;

    public StudentsTestRunnerServiceImpl(StudentsTestService studentsTestService, QuestionStreamService questionStreamService){
        this.studentsTestService = studentsTestService;
        this.questionStreamService = questionStreamService;
    }

    public void run(String[] args){
        questionStreamService.writeAgreement();
        String fio = questionStreamService.readFIO();

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

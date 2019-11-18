package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.service.StudentsTestService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StudentsTestService")
@SpringBootTest
class SpringBootStudentServiceTests {

    @Autowired
    StudentsTestService studentsTestService;


    @DisplayName("Первый вопрос и ответ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testFirstQuestion() {
        String question = studentsTestService.getCurrentQuestion();
        System.out.println("question="+question);
        assertThat(question).startsWith("1)");
        String answer = studentsTestService.getCurrentAnswer();
        System.out.println("answer="+answer);
        assertThat(answer).isEqualTo("b");
    }

    @DisplayName("Второй вопрос и ответ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testSecondQuestion() {
        studentsTestService.getCurrentQuestion();
        String question = studentsTestService.getCurrentQuestion();
        System.out.println("question="+question);
        assertThat(question).startsWith("2)");
        String answer = studentsTestService.getCurrentAnswer();
        System.out.println("answer="+answer);
        assertThat(answer).isEqualTo("b");
    }

    @DisplayName("Проверка прохождения")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testAnswer() {
        studentsTestService.getCurrentQuestion();
        studentsTestService.checkCurrentAnswer("b");
        studentsTestService.getCurrentQuestion();
        studentsTestService.checkCurrentAnswer("b");
        studentsTestService.getCurrentQuestion();
        studentsTestService.checkCurrentAnswer("a");
        studentsTestService.getCurrentQuestion();
        studentsTestService.checkCurrentAnswer("c");
        studentsTestService.getCurrentQuestion();
        studentsTestService.checkCurrentAnswer("a");

        String result = studentsTestService.getCurrentQuestion();
        assertThat(studentsTestService.isFlagTestFinished()).isEqualTo(true);
        System.out.println("result="+result);
        assertThat(result).contains("Тест успешно сдан");
    }


    @DisplayName("Тест завершен")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void testCount() {
        for(int i=1; i<= 6; i++){
            String question = studentsTestService.getCurrentQuestion();
        }
        assertThat(studentsTestService.isFlagTestFinished()).isEqualTo(true);
    }


}

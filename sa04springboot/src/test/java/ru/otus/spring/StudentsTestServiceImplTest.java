package ru.otus.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.QuestionDaoImpl;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.StudentsTestServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentsTestServiceImplTest {

    @MockBean
    private QuestionDaoImpl questionDao;

    @Autowired
    private StudentsTestServiceImpl studentsTestServiceImpl;

    @BeforeEach
    public void init() {
        Question q = new Question("1) Неклеточные формы жизни: a) бактерии b)вирусы c) грибы", "b");
        Mockito.when(questionDao.getQuestionByNumber(1)).thenReturn(q);
    }

    @Test
    public void testQuestion() {
        assertEquals("1) Неклеточные формы жизни: a) бактерии b)вирусы c) грибы", studentsTestServiceImpl.getCurrentQuestion());

    }


}

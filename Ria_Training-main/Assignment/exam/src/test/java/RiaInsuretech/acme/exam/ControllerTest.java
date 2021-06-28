package RiaInsuretech.acme.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import RiaInsuretech.acme.exam.Controllers.ExamController;
import RiaInsuretech.acme.exam.Entities.Question;
import RiaInsuretech.acme.exam.MyService.ExamService;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class ControllerTest {
    @MockBean
    ExamService examService;
    @Autowired
    ExamController examController;

    @BeforeEach
    void init() throws Exception {
        MockitoAnnotations.initMocks(this); // without this you will get NPE

    }

    @Test
    public void examStartTest() throws Exception {
        RiaInsuretech.acme.exam.Entities.Question question = new RiaInsuretech.acme.exam.Entities.Question("a", "a",
                "a", "a", "a", "a");
        when(examService.startExam(anyString(), anyString())).thenReturn(question);
        when(examService.getUsername()).thenReturn("2");// tExam(anyString(),
        Question ques = examController.ExamStart("jak");
        // assertNotNull(examController.ExamStart("jak"));
        assertEquals("a", ques.ques);
        assertNotNull(examController.firstPage());
    }

    @Test
    public void examGetNextquesTest() throws Exception {
        RiaInsuretech.acme.exam.Entities.Question question = new RiaInsuretech.acme.exam.Entities.Question("a", "a",
                "a", "a", "a", "a");
        when(examService.getNextQuestion(anyString(), anyString(), anyString())).thenReturn(question);// tExam(anyString(),
        when(examService.getScore(anyString(), anyString())).thenReturn("2");// tExam(anyString(),
        when(examService.getUsername()).thenReturn("2");// tExam(anyString(),
        assertNotNull(examController.getNext("subject", "pass1"));

    }

    @Test
    public void examGetScoreTest() throws Exception {
        RiaInsuretech.acme.exam.Entities.Question question = new RiaInsuretech.acme.exam.Entities.Question("a", "a",
                "a", "a", "a", "a");
        when(examService.getScore(anyString(), anyString())).thenReturn("2");// tExam(anyString(),
        when(examService.getUsername()).thenReturn("2");// tExam(anyString(),
        assertNotNull(examController.getScore("subject"));

    }

}
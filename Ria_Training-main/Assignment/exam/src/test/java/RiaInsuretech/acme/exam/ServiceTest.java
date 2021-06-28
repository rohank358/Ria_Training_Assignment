package RiaInsuretech.acme.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import RiaInsuretech.acme.exam.Entities.MyExaminer;
import RiaInsuretech.acme.exam.Entities.MyUser;
import RiaInsuretech.acme.exam.Entities.Question;
import RiaInsuretech.acme.exam.Entities.QuestionModel;
import RiaInsuretech.acme.exam.Entities.SubjectsData;
import RiaInsuretech.acme.exam.MyRepositories.ExaminerRepository;
import RiaInsuretech.acme.exam.MyRepositories.MyUserRepository;
import RiaInsuretech.acme.exam.MyRepositories.SubjectRepository;
import RiaInsuretech.acme.exam.MyService.ExamService;

@SpringBootTest()
// @ExtendWith(MockitoExtension.class)
// @RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class ServiceTest {
    @MockBean
    private SubjectRepository Subjects;
    @MockBean
    private MyUserRepository Users;
    @MockBean
    private ExaminerRepository Examiners;
    @Autowired
    // @InjectMocks
    private ExamService examService;

    MyUser currUser = new MyUser("user1", "pass1", "exam1");

    @BeforeEach
    void initUseCase() {
        MockitoAnnotations.initMocks(this); // without this you will get NPE

    }

    @Test
    public void startExamForFirstTime() {
        try {
            MyUser currUser = new MyUser("user1", "pass1", "exam1");
            SubjectsData Math1 = new SubjectsData("Math1");
            QuestionModel q1 = new QuestionModel("find", "1 : 10 : 3 : 12", "a", "1");
            Math1.addQuestion(q1);
            Optional<SubjectsData> optionalSubject = Optional.of(Math1);
            Mockito.when(Subjects.findById(anyString())).thenReturn(optionalSubject);

            currUser = new MyUser("user1", "pass1", "exam1");
            Optional<MyUser> optionalUser = Optional.ofNullable(currUser);//
            when(Users.findById(anyString())).thenReturn(optionalUser);
            Question first = examService.startExam("Math1", "user1");

        } catch (Exception e) {
            fail("The Exam should have been intitiated for first time");
        }

    }

    @Test
    public void startExamForSecondTime() throws Exception {

        try {
            // INIT
            // Question qret = new Question("1,1", "ques", "A", "B", "C", "D");
            MyUser currUser = new MyUser("user1", "pass1", "exam1");
            SubjectsData Math1 = new SubjectsData("Math1");
            QuestionModel q1 = new QuestionModel("find", "1 : 10 : 3 : 12", "a", "1");
            Math1.addQuestion(q1);
            Optional<SubjectsData> optionalSubject = Optional.of(Math1);
            Mockito.when(Subjects.findById(anyString())).thenReturn(optionalSubject);

            currUser.addSubject("Math1");
            Optional<MyUser> optionalUser = Optional.of(currUser);

            when(Users.findById(anyString())).thenReturn(optionalUser);

            examService.startExam("Math1", "user1");
            // assertThrows(message, expectedThrowable, runnable)
        } catch (Exception e) {

            assertTrue(e.getMessage().equals("User has already appeared in exam"));
        }

    }

    @Test // (expected = NullPointerException.class)
    public void getNextQuestionForCorrectAns() throws Exception {
        MyUser currUser = new MyUser("user1", "pass1", "exam1");

        Optional<MyUser> optionalUser = Optional.of(currUser);// of(currUser);
        SubjectsData Math1 = new SubjectsData("Math1");
        QuestionModel q1 = new QuestionModel("find", "1 : 10 : 3 : 12", "a", "1");
        Math1.addQuestion(q1);
        Math1.addQuestion(q1);
        QuestionModel q2 = new QuestionModel("What", "1 : 10 : 3 : 12", "a", "2");
        Math1.addQuestion(q2);
        Math1.addQuestion(q2);
        Optional<SubjectsData> optionalSubject = Optional.of(Math1);
        MyExaminer examiner = new MyExaminer("exam1", "pass1");
        Optional<MyExaminer> optionalExaminer = Optional.of(examiner);
        // assertEquals(optionalSubject.get().getQuestion("1,2").getId(), "1.0");

        Mockito.when(Subjects.findById(anyString())).thenReturn(optionalSubject);
        Mockito.when(Examiners.findById(anyString())).thenReturn(optionalExaminer);

        Mockito.when(Users.findById(anyString())).thenReturn(optionalUser);

        Mockito.when(Users.save(any(MyUser.class))).thenReturn(null);
        Mockito.when(Examiners.findById(anyString())).thenReturn(optionalExaminer);// .get().getNumberOfQustions()).thenReturn(5);
        RiaInsuretech.acme.exam.Entities.Question first = examService.startExam("Math1", "user1");
        assertEquals(first.getId(), "1.0");
        RiaInsuretech.acme.exam.Entities.Question second = examService.getNextQuestion("user1", "a", "Math1");
        assertEquals(second.getId(), "1.1");
    }

    @Test
    public void getNextQuestionForIncorrectAns() throws Exception {
        MyUser currUser = new MyUser("user1", "pass1", "exam1");

        Optional<MyUser> optionalUser = Optional.of(currUser);// of(currUser);
        SubjectsData Math1 = new SubjectsData("Math1");
        QuestionModel q1 = new QuestionModel("find", "1 : 10 : 3 : 12", "a", "1");
        Math1.addQuestion(q1);
        Math1.addQuestion(q1);
        QuestionModel q2 = new QuestionModel("What", "1 : 10 : 3 : 12", "a", "2");
        Math1.addQuestion(q2);
        Math1.addQuestion(q2);
        Optional<SubjectsData> optionalSubject = Optional.of(Math1);
        MyExaminer examiner = new MyExaminer("exam1", "pass1");
        Optional<MyExaminer> optionalExaminer = Optional.of(examiner);
        // assertEquals(optionalSubject.get().getQuestion("1,2").getId(), "1.0");

        Mockito.when(Subjects.findById(anyString())).thenReturn(optionalSubject);
        Mockito.when(Examiners.findById(anyString())).thenReturn(optionalExaminer);

        Mockito.when(Users.findById(anyString())).thenReturn(optionalUser);

        Mockito.when(Users.save(any(MyUser.class))).thenReturn(null);
        Mockito.when(Examiners.findById(anyString())).thenReturn(optionalExaminer);// .get().getNumberOfQustions()).thenReturn(5);
        RiaInsuretech.acme.exam.Entities.Question first = examService.startExam("Math1", "user1");
        assertEquals(first.getId(), "1.0");

        Question second = examService.getNextQuestion("user1", "b", "Math1");
        assertEquals(second.getId(), "1.2");
    }

    @Test
    public void getScoreTest() throws Exception {
        MyUser currUser = new MyUser("user1", "pass1", "exam1");
        currUser.addSubject("Math1");
        currUser.setScore("Math1", 10);
        Optional<MyUser> optionalUser = Optional.of(currUser);// of(currUser);
        MyExaminer examiner = new MyExaminer("exam1", "pass1");
        Optional<MyExaminer> optionalExaminer = Optional.of(examiner);
        Mockito.when(Examiners.findById(anyString())).thenReturn(optionalExaminer);// .get().getNumberOfQustions()).thenReturn(5);
        Mockito.when(Users.findById(anyString())).thenReturn(optionalUser);

        // examService.startExam("Math1", "user1");
        String score = examService.getScore("Math1", "pass1");
        assertEquals(score, "47");

    }

}

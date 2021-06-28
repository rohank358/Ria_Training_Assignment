// package RiaInsuretech.acme.exam.SampleTest;

// import static org.junit.Assert.assertEquals;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.core.annotation.Order;

// import RiaInsuretech.acme.exam.Entities.Question;
// import RiaInsuretech.acme.exam.Entities.QuestionModel;
// import RiaInsuretech.acme.exam.MyRepositories.*;
// import RiaInsuretech.acme.exam.MyService.ExamService;

// public class JunitTest1 {
// @Autowired
// ExaminerRepository examinerRepository;
// @Autowired
// private SubjectRepository subjectRepository;
// @Autowired
// private MyUserRepository myUserRepository;
// @Autowired
// ExamService examService;

// @Test
// @Order(1)
// public void testAddUsers() {

// examService.addSubject("Math1");
// examService.addExaminer("examiner1", "pass1");
// examService.addUser("user1", "pass1", "examiner1");
// assertEquals(true, subjectRepository.existsById("Math1"));
// assertEquals(true, examinerRepository.existsById("examiner1"));
// assertEquals(true, myUserRepository.existsById("user1"));
// }

// @Test
// @Order(2)
// public void testAddQuestion() {
// List<QuestionModel> myList1 = new ArrayList<QuestionModel>();

// QuestionModel q1 = new QuestionModel("What is the value of 10+2", "1 : 10 : 3
// : 12", "d", "1");
// myList1.add(q1);
// QuestionModel q2 = new QuestionModel("What is the value of 20+2", "5 : 22 : 3
// : 12", "b", "1");
// myList1.add(q2);
// QuestionModel q3 = new QuestionModel("What is the value of 30+2", "1 : 10 :
// 32 : 12", "c", "1");
// myList1.add(q3);

// QuestionModel q4 = new QuestionModel("What is the value of 40+2", "1 : 42 : 3
// : 12", "b", "1");
// myList1.add(q4);

// QuestionModel q5 = new QuestionModel("What is the value of 50+2", "1 : 52 : 3
// : 12", "b", "1");
// myList1.add(q5);

// QuestionModel q6 = new QuestionModel("What is the value of 60+2", "1 : 10 : 3
// : 12", "d", "1");
// myList1.add(q6);

// QuestionModel q7 = new QuestionModel("What is the value of 70+2", "1 : 10 :
// 72 : 12", "c", "1");
// myList1.add(q7);

// QuestionModel q8 = new QuestionModel("What is the value of 80+2", "1 : 10 : 3
// : 82", "d", "1");
// myList1.add(q8);

// QuestionModel q9 = new QuestionModel("What is the value of 90+2", "1 : 10 : 3
// : 92", "d", "1");
// myList1.add(q9);

// QuestionModel q10 = new QuestionModel("What is the value of 75+2", "1 : 72 :
// 3 : 12", "b", "1");
// myList1.add(q10);

// List<QuestionModel> myList2 = new ArrayList<QuestionModel>();

// QuestionModel L2q1 = new QuestionModel("What is the value of 100+10+2", "1 :
// 10 : 3 : 112", "d", "2");
// myList2.add(L2q1);
// QuestionModel L2q2 = new QuestionModel("What is the value of 100+20+2", "5 :
// 122 : 3 : 12", "b", "2");
// myList2.add(L2q2);
// QuestionModel L2q3 = new QuestionModel("What is the value of 100+30+2", "1 :
// 10 : 132 : 12", "c", "2");
// myList2.add(L2q3);

// QuestionModel L2q4 = new QuestionModel("What is the value of 100+40+2", "1 :
// 142 : 3 : 12", "b", "2");
// myList2.add(L2q4);

// QuestionModel L2q5 = new QuestionModel("What is the value of 100+50+2", "1 :
// 152 : 3 : 12", "b", "2");
// myList2.add(L2q5);

// QuestionModel L2q6 = new QuestionModel("What is the value of 100+60+2", "1 :
// 10 : 3 : 112", "d", "2");
// myList2.add(L2q6);

// QuestionModel L2q7 = new QuestionModel("What is the value of 100+70+2", "1 :
// 10 : 172 : 12", "c", "2");
// myList2.add(L2q7);

// QuestionModel L2q8 = new QuestionModel("What is the value of 100+80+2", "1 :
// 10 : 3 : 182", "d", "2");
// myList2.add(L2q8);

// QuestionModel L2q9 = new QuestionModel("What is the value of 100+90+2", "1 :
// 10 : 3 : 192", "d", "2");
// myList2.add(L2q9);

// QuestionModel L2q10 = new QuestionModel("What is the value of 100+75+2", "1 :
// 172 : 3 : 12", "b", "2");
// myList2.add(L2q10);

// List<QuestionModel> myList3 = new ArrayList<QuestionModel>();

// QuestionModel L3q1 = new QuestionModel("What is the value of 100+10+2", "1 :
// 10 : 3 : 1112", "d", "3");
// myList3.add(L3q1);
// QuestionModel L3q2 = new QuestionModel("What is the value of 1000+100+20+2",
// "5 : 1122 : 3 : 12", "b", "3");
// myList3.add(L3q2);
// QuestionModel L3q3 = new QuestionModel("What is the value of 1000+100+30+2",
// "1 : 10 : 1132 : 12", "c", "3");
// myList3.add(L3q3);

// QuestionModel L3q4 = new QuestionModel("What is the value of 1000+100+40+2",
// "1 : 1142 : 3 : 12", "b", "3");
// myList3.add(L3q4);

// QuestionModel L3q5 = new QuestionModel("What is the value of 1000+100+50+2",
// "1 : 1152 : 3 : 12", "b", "3");
// myList3.add(L3q5);

// QuestionModel L3q6 = new QuestionModel("What is the value of 1000+100+60+2",
// "1 : 10 : 3 : 1112", "d", "3");
// myList3.add(L3q6);

// QuestionModel L3q7 = new QuestionModel("What is the value of 1000+100+70+2",
// "1 : 10 : 1172 : 12", "c", "3");
// myList3.add(L3q7);

// QuestionModel L3q8 = new QuestionModel("What is the value of 1000+100+80+2",
// "1 : 10 : 3 : 1182", "d", "3");
// myList3.add(L3q8);

// QuestionModel L3q9 = new QuestionModel("What is the value of 1000+100+90+2",
// "1 : 10 : 3 : 1192", "d", "3");
// myList3.add(L3q9);

// QuestionModel L3q10 = new QuestionModel("What is the value of 1000+
// 100+75+2", "1 : 1172 : 3 : 12", "b", "3");
// myList3.add(L3q10);

// examService.addLevel(myList1, 1, "Math1");
// examService.addLevel(myList2, 2, "Math1");
// examService.addLevel(myList3, 3, "Math1");

// int a1 = subjectRepository.findById("Math1").get().testQuestions1.size();
// int a2 = subjectRepository.findById("Math1").get().testQuestions2.size();
// int a3 = subjectRepository.findById("Math1").get().testQuestions3.size();

// assertEquals(10, a1);
// assertEquals(10, a3);
// assertEquals(10, a2);
// QuestionModel t1 = new QuestionModel("What is the value of 75+20", "1 : 95 :
// 3 : 12", "b", "1");
// QuestionModel t3 = new QuestionModel("What is the value of 100+75+5", "1 :
// 180 : 3 : 12", "b", "2");
// QuestionModel t2 = new QuestionModel("What is the value of 100+100+75+2", "1
// : 277 : 3 : 12", "b", "3");

// examService.addQuestion(t1, "Math1");
// examService.addQuestion(t2, "Math1");
// examService.addQuestion(t3, "Math1");

// a1 = subjectRepository.findById("Math1").get().testQuestions1.size();
// a2 = subjectRepository.findById("Math1").get().testQuestions2.size();
// a3 = subjectRepository.findById("Math1").get().testQuestions3.size();

// assertEquals(11, a1);
// assertEquals(11, a3);
// assertEquals(11, a2);
// }

// @Test
// @Order(3)
// public void testUser() throws Exception {
// examService.setLevel("examiner1", 3);
// examService.setTotalQuestions("examiner1", 10);
// // d b c
// Question q1 = examService.startExam("Math1", "user1");

// Question q2 = examService.getNextQuestion("user1", "d", "Math1");
// Question q3 = examService.getNextQuestion("user1", "b", "Math1");

// assertEquals("1.0", q1.id);
// assertEquals("1.1", q2.id);
// assertEquals("1.1.1", q3.id);
// Question q4 = examService.getNextQuestion("user1", "c", "Math1");// give
// wrong ans
// Question q5 = examService.getNextQuestion("user1", "a", "Math1");// give
// correct ans
// Question q6 = examService.getNextQuestion("user1", "b", "Math1");// give
// wrong ans
// assertEquals("2.0", q4.id);
// assertEquals("2.2", q5.id);
// assertEquals("2.2.1", q6.id);

// Question q7 = examService.getNextQuestion("user1", "a", "Math1");
// Question q8 = examService.getNextQuestion("user1", "c", "Math1");
// Question q9 = examService.getNextQuestion("user1", "c", "Math1");
// assertEquals("3.0", q7.id);
// assertEquals("3.1", q8.id);
// assertEquals("3.1.2", q9.id);
// Question q10 = examService.getNextQuestion("user1", "d", "Math1");
// Question q11 = examService.getNextQuestion("user1", "d", "Math1");
// assertEquals("4.0", q10.id);
// assertEquals("0", q11.id);
// String score = examService.getScore("Math1", "user1");
// assertEquals("52", score);
// }

// }
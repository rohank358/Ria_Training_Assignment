// package RiaInsuretech.acme.exam;

// import static org.junit.Assert.assertEquals;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.ObjectWriter;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.nimbusds.jose.JWSObject;

// import org.hamcrest.Matchers;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpHeaders;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// @SpringBootTest()
// @RunWith(MockitoJUnitRunner.class)
// @AutoConfigureMockMvc
// class ExamApplicationTests {
// @Autowired
// private MockMvc mvc;

// @Test
// void NotAuthorisedTestForExamUrls() throws Exception {
// mvc.perform(MockMvcRequestBuilders.get("/Hello")).andExpect(status().isOk());
// mvc.perform(MockMvcRequestBuilders.get("/Exam/Start")).andExpect(status().isForbidden());
// mvc.perform(MockMvcRequestBuilders.get("/Exam/GetNext")).andExpect(status().isForbidden());
// mvc.perform(MockMvcRequestBuilders.get("/Exam/Score")).andExpect(status().isForbidden());

// }

// @Test
// public void testUserExamination() throws Exception {
// // resetting the database
// mvc.perform(MockMvcRequestBuilders.get("/Hello")).andExpect(status().isOk());

// AuthenticationRequest tRequest = new AuthenticationRequest("user1", "pass1");
// ObjectMapper mapper = new ObjectMapper();
// mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
// ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
// String requestJson = ow.writeValueAsString(tRequest);

// MvcResult tResponse = mvc
// .perform(post("/Authenticate").contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)
// .content(requestJson).accept(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))

// .andExpect(content().contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8))
// .andExpect(status().isOk()).andExpect(jsonPath("$.jwt",
// Matchers.notNullValue())).andReturn();//
// .contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8)

// String token = "Bearer "
// + mapper.readValue(tResponse.getResponse().getContentAsString(),
// AuthenticationResponse.class).getJwt();

// MvcResult ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/Start").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("subject", "Math1")).andExpect(status().isOk()).andReturn();
// String id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("1.0", id);

// /// Testing the GetNextQuestion
// /// For the correct answers
// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "a").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("1.1", id);

// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "a").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("1.1.1", id);

// /// For mixed answers
// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "a").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("2.0", id);

// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "a").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("2.1", id);
// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "c").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("2.1.2", id);

// /// For all wrong answers
// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "a").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("3.0", id);

// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "c").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("3.2", id);
// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "c").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("3.2.2", id);

// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "c").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("4.0", id);

// ques =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/nextQuestion").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("ans", "c").param("subject",
// "Math1")).andExpect(status().isOk()).andReturn();
// id = mapper.readValue(ques.getResponse().getContentAsString(),
// Question.class).getId();
// assertEquals("0", id);

// /// Finally checking the scores
// MvcResult res =
// mvc.perform(MockMvcRequestBuilders.get("/Exam/Score").header(HttpHeaders.AUTHORIZATION,
// token)
// .param("subject", "Math1")).andExpect(status().isOk()).andReturn();

// String score = mapper.readValue(res.getResponse().getContentAsString(),
// String.class);

// assertEquals("42", score);
// }

// }

// // examService.setLevel("examiner1", 3);
// // examService.setTotalQuestions("examiner1", 10);
// // // d b c
// // Question q1 = examService.startExam("Math1", "user1");

// // Question q2 = examService.getNextQuestion("user1", "d", "Math1");
// // Question q3 = examService.getNextQuestion("user1", "b", "Math1");

// // assertEquals("1.0", q1.id);
// // assertEquals("1.1", q2.id);
// // assertEquals("1.1.1", q3.id);
// // Question q4 = examService.getNextQuestion("user1", "c", "Math1");// give
// // wrong ans
// // Question q5 = examService.getNextQuestion("user1", "a", "Math1");// give
// // correct ans
// // Question q6 = examService.getNextQuestion("user1", "b", "Math1");// give
// // wrong ans
// // assertEquals("2.0", q4.id);
// // assertEquals("2.2", q5.id);
// // assertEquals("2.2.1", q6.id);

// // Question q7 = examService.getNextQuestion("user1", "a", "Math1");
// // Question q8 = examService.getNextQuestion("user1", "c", "Math1");
// // Question q9 = examService.getNextQuestion("user1", "c", "Math1");
// // assertEquals("3.0", q7.id);
// // assertEquals("3.1", q8.id);
// // assertEquals("3.1.2", q9.id);
// // Question q10 = examService.getNextQuestion("user1", "d", "Math1");
// // Question q11 = examService.getNextQuestion("user1", "d", "Math1");
// // assertEquals("4.0", q10.id);
// // assertEquals("0", q11.id);
// // String score = examService.getScore("Math1", "user1");
// // assertEquals("52", score);
// // examService.deleteUser("user1");
// // examService.deleteExaminer("examiner1");
// // examService.deleteSubject("Math1");

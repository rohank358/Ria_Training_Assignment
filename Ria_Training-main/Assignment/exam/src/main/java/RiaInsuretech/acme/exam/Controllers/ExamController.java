package RiaInsuretech.acme.exam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import RiaInsuretech.acme.exam.ConfigSecurity.AuthenticationRequest;
import RiaInsuretech.acme.exam.ConfigSecurity.AuthenticationResponse;
import RiaInsuretech.acme.exam.ConfigSecurity.JwtUtil;
import RiaInsuretech.acme.exam.ConfigSecurity.MyUserDetailsService;
//import RiaInsuretech.acme.exam.MyRepositories.*;
import RiaInsuretech.acme.exam.Entities.*;
import RiaInsuretech.acme.exam.MyService.AddData;
import RiaInsuretech.acme.exam.MyService.ExamService;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 360000)
@RestController
public class ExamController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;
    // @Autowired
    // private dollerRepo DollerRepo;
    @Autowired
    private ExamService examService;
    @Autowired
    private AddData addData;

    // Mappings for the ACME manager

    @PostMapping("/ACME/AddSubject")
    public String addSubject(@RequestParam String subject) {
        examService.addSubject(subject);
        return "added Subject successfully";
    }

    @PostMapping("/ACME/AddExaminer")
    public String addExaminers(@RequestParam String username, @RequestParam String password) {
        examService.addExaminer(username, password);
        return "added Examiner successfully";
    }

    @DeleteMapping("/ACME/DeleteExaminer")
    public String DeleteExaminer(@RequestParam String username) {
        examService.deleteExaminer(username);
        return "deleted Examiner successfully";
    }

    // Only Admin can delete users
    @DeleteMapping("/ACME/DeleteUser")
    public String DeleteUsers(@RequestParam String username) {
        examService.deleteUser(username);
        return "deleted user successfully";
    }

    @PostMapping("/ACME/AddLevel/{level}/{subject}")
    public String addlevel(@RequestBody List<QuestionModel> list, @PathVariable int level,
            @PathVariable String subject) {

        examService.addLevel(list, level, subject);
        return "added level successfully";
    }

    @PostMapping("/ACME/AddQuestion/{subject}")
    public String addQues(@RequestBody QuestionModel Question, @PathVariable String subject) {
        examService.addQuestion(Question, subject);
        return "added question successfully";
    }

    @PutMapping("/ACME/UpdateQuestion/{subject}")
    public String updateQues(@RequestBody QuestionModel Question, @PathVariable String subject) {
        examService.updateQuestion(Question, subject);
        return "added level successfully";
    }

    @DeleteMapping("/ACME/DeleteQuestion/{subject}/{id}")
    public String deleteQues(@PathVariable String subject, @PathVariable String id) {
        examService.deleteQuestion(subject, id);
        return "delted qustion successfully";
    }

    // Examiner mappings
    @PutMapping("/Examiner/AddUser")
    public String addUsers(@RequestParam String username, @RequestParam String password) {
        examService.addUser(username, password, examService.getUsername());
        return "added user successfully";
    }

    @PutMapping("/Examiner/SetLevel")
    public String setLevels(@RequestParam int level) {
        examService.setLevel(examService.getUsername(), level);
        return "changed level successfully";
    }

    @PutMapping("/Examiner/TotalQuestion")
    public String totalQuestion(@RequestParam int total) {
        examService.setTotalQuestions(examService.getUsername(), total);
        return "changed total no of questions successfully";
    }

    // User mappings
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/Exam/Start")
    public Question ExamStart(@RequestParam String subject) throws Exception {

        return examService.startExam(subject, examService.getUsername());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/Exam/nextQuestion")
    public Question getNext(@RequestParam String ans, @RequestParam String subject) {
        return examService.getNextQuestion(examService.getUsername(), ans, subject);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/Exam/Score")
    public String getScore(@RequestParam String subject) {

        return examService.getScore(subject, examService.getUsername());
    }

    @GetMapping({ "/Hello" })
    public String firstPage() {// @RequestBody usDoller usd) {
        // List<simple> sim = DollerRepo.findById("first").get().list;
        addData.addAll();
        return "Hello World ";

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

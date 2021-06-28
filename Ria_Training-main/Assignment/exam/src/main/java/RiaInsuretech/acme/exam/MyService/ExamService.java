package RiaInsuretech.acme.exam.MyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import RiaInsuretech.acme.exam.MyRepositories.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import RiaInsuretech.acme.exam.Entities.*;

@Service
public class ExamService {

    @Autowired
    private SubjectRepository Subjects;
    @Autowired
    private MyUserRepository Users;
    @Autowired
    private ExaminerRepository Examiners;

    public ExamService(SubjectRepository subjects2, MyUserRepository users2, ExaminerRepository examiners2) {
    }

    // currently logged in user
    public String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    // updating database questions

    public Question getById(String id, String subject) {
        return Subjects.findById(subject).get().getQuestion(id);
    }

    public List<QuestionModel> getAllQuestions(String subject) {
        return Subjects.findById(subject).get().getAllQuestions();
    }

    // Acme questions and subjects handling
    public void addSubject(String subject) {
        SubjectsData sub = new SubjectsData(subject);
        Subjects.save(sub);
    }

    public void deleteSubject(String subject) {
        Subjects.deleteById(subject);
    }

    public void addLevel(List<QuestionModel> list, int level, String subject) {
        SubjectsData sub = Subjects.findById(subject).get();
        sub.addLevel(level, list);
        Subjects.save(sub);
    }

    public void addQuestion(QuestionModel question, String subject) {
        SubjectsData sub = Subjects.findById(subject).get();
        sub.addQuestion(question);
        Subjects.save(sub);
    }

    public void updateQuestion(QuestionModel question, String subject) {
        SubjectsData sub = Subjects.findById(subject).get();
        String id = question.getId();
        sub.updateQuestion(id, question);
        Subjects.save(sub);
    }

    public void deleteQuestion(String subject, String id) {
        SubjectsData sub = Subjects.findById(subject).get();
        sub.deleteById(id);
        Subjects.save(sub);
    }

    public void addExaminer(String username, String password) {
        MyExaminer myExaminer = new MyExaminer(username, password);
        Examiners.save(myExaminer);
    }

    public void deleteUser(String username) {
        Users.deleteById(username);

    }

    public void deleteExaminer(String username) {
        Examiners.deleteById(username);
    }

    // Methods for the examiners

    public void addUser(String username, String password, String examId) {
        MyUser user = new MyUser(username, password, examId);
        Users.save(user);
    }

    public void setLevel(String username, int level) {
        MyExaminer myExaminer = Examiners.findById(username).get();
        myExaminer.setLevelOfQustion(level);
        Examiners.save(myExaminer);
    }

    public void setTotalQuestions(String username, int total) {
        MyExaminer myExaminer = Examiners.findById(username).get();
        myExaminer.setNumberOfQustions(total);
        Examiners.save(myExaminer);
    }

    // Methods for User

    public String getScore(String subject, String username) {
        MyUser myUser = Users.findById(username).get();
        int totalQues = Examiners.findById(myUser.examId).get().getNumberOfQustions();
        int level = Examiners.findById(myUser.examId).get().getLevelOfQustion();

        int total = (totalQues * (level + 1)) / 2;
        int rem = totalQues % level;
        total = ((rem * (rem + 1)) / 2) + (total);

        int score = (myUser.getScore(subject) * 100) / total;

        return Integer.toString(score);
    }

    public Question startExam(String subject, String username) throws Exception {

        MyUser user = Users.findById(username).get();
        if (user.addSubject(subject)) {

            user.setLastQuestion("1,0", 1);
            user.resetCount();
            Users.save(user);

            Question ques = Subjects.findById(subject).get().getQuestion("1,1");
            ques.setId("1.0");
            return ques;
        } else
            throw new Exception("User has already appeared in exam");

    }

    public Question getNextQuestion(String email, String ans, String subject) {

        // retrieving the required data from the mongo database
        Question next = new Question();
        MyUser user = Users.findById(email).get();
        String examinerId = user.getExamId();
        MyExaminer examiner = Examiners.findById(examinerId).get();
        int maxLevel = examiner.getLevelOfQustion();

        int NewCount = user.updateCount();

        String UserQuesId = user.getLastQuestion();
        String[] tem = UserQuesId.split(",");
        if (tem[1].equals("0"))
            UserQuesId = tem[0];

        // System.out.println(UserQuesId + " raam");
        int newScore = 0;

        int weight = user.getWeight();
        String currID = Integer.toString(weight) + "," + Integer.toString(NewCount - 1);
        String answer = Subjects.findById(subject).get().getAnswer(currID);

        String nextID = "";

        // check if the no of questions to be asked are less than total no. of
        int NewWeight = weight;
        if (NewCount < examiner.getNumberOfQustions()) {
            // Check if the answer is correct and give next question accordingly
            if (answer.equalsIgnoreCase(ans)) {
                newScore += weight; // update the score of user
                if (tem.length < maxLevel || (maxLevel > 1 && tem[1].equals("0"))) {

                    UserQuesId = UserQuesId + ",1";

                    nextID = Integer.toString(weight + 1) + "," + Integer.toString(NewCount);
                    next = Subjects.findById(subject).get().getQuestion(nextID);
                    NewWeight++;
                } else {

                    int a = Integer.parseInt(tem[0]) + 1;
                    UserQuesId = String.valueOf(a) + ",0";
                    nextID = Integer.toString(1) + "," + Integer.toString(NewCount);
                    next = Subjects.findById(subject).get().getQuestion(nextID);
                    NewWeight--;
                }

            } else {
                NewWeight = 1;
                if (tem.length < maxLevel || (maxLevel > 1 && tem[1].equals("0"))) {

                    UserQuesId = UserQuesId + ",2";
                    nextID = Integer.toString(1) + "," + Integer.toString(NewCount);
                    next = Subjects.findById(subject).get().getQuestion(nextID);
                } else {

                    int a = Integer.parseInt(tem[0]) + 1;
                    UserQuesId = String.valueOf(a) + ",0";
                    nextID = Integer.toString(1) + "," + Integer.toString(NewCount);
                    next = Subjects.findById(subject).get().getQuestion(nextID);
                }
            }

            user.setScore(subject, newScore);
            user.setLastQuestion(UserQuesId, NewWeight);
            UserQuesId = format(UserQuesId);
            next.setId(UserQuesId);
        } else {

            // here the exam is ended
            if (ans.equals(answer))
                newScore++;
            next.setId("0");
            user.setScore(subject, newScore);
            user.resetCount();
            user.setLastQuestion("1,0", 1);

        }

        Users.save(user);
        return next;
    }

    private String format(String userQuesId) {

        return userQuesId.replaceAll(",", ".");
    }

}
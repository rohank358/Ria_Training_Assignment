package RiaInsuretech.acme.exam.Entities;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MyUser")
public class MyUser {

    @Id
    public String username;

    public String password;

    public String examId;

    public int count;

    public List<String> subjects;
    public List<Integer> scores;

    public String lastQuestion;
    public int weight;

    public MyUser(String username, String password, String examId) {
        this.username = username;
        this.password = password;
        this.examId = examId;
        count = 0;
        subjects = new ArrayList<String>();
        scores = new ArrayList<Integer>();
        lastQuestion = "1.0";
        weight = 1;
    }

    public int updateCount() {
        count++;
        return count;
    }

    public void resetCount() {
        count = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String username) {
        this.username = username;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore(String subject) {
        int i = subject.indexOf(subject);
        if (i != -1) {
            return scores.get(i);
        } else
            return 0;
    }

    public void setScore(String subject, int score) {
        int i = subject.indexOf(subject);
        if (i != -1)
            scores.set(i, scores.get(i) + score);

    }

    public boolean addSubject(String subject) {
        if (!subjects.contains(subject)) {
            scores.add(0);
            subjects.add(subject);
            return true;
        } else
            return false;

    }

    public String getLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(String lastQuestion, int weight) {
        this.lastQuestion = lastQuestion;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

}

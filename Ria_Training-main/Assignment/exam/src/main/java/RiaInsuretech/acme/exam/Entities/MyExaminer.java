package RiaInsuretech.acme.exam.Entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExaminerDetails")
public class MyExaminer {
    @Id
    @Size(max = 20)
    String examId;
    @Size(max = 20)
    String password;
    @Max(value = 10)
    int numberOfQustions;
    @Max(value = 10)
    int levelOfQustion;

    public MyExaminer(String examId, String password) {
        this.examId = examId;
        this.password = password;
        numberOfQustions = 10;
        levelOfQustion = 3;
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

    public int getNumberOfQustions() {
        return numberOfQustions;
    }

    public void setNumberOfQustions(int numberOfQustions) {
        this.numberOfQustions = numberOfQustions;
    }

    public int getLevelOfQustion() {
        return levelOfQustion;
    }

    public void setLevelOfQustion(int levelOfQustion) {
        this.levelOfQustion = levelOfQustion;
    }

}

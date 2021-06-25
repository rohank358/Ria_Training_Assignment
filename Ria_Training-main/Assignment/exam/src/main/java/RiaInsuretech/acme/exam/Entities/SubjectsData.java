package RiaInsuretech.acme.exam.Entities;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubjectsData")
public class SubjectsData {
    @Id
    public String subject;
    // public HashMap<String, QuestionModel> testQuestions;
    public List<QuestionModel> testQuestions1;
    public List<QuestionModel> testQuestions2;
    public List<QuestionModel> testQuestions3;
    public List<QuestionModel> testQuestions4;

    public SubjectsData(String subject) {
        this.subject = subject;
        // testQuestions = new HashMap<String, QuestionModel>();
        testQuestions1 = new ArrayList<QuestionModel>();
        testQuestions2 = new ArrayList<QuestionModel>();
        testQuestions3 = new ArrayList<QuestionModel>();
        testQuestions4 = new ArrayList<QuestionModel>();
    }

    public void addQuestion(QuestionModel ques) {
        // testQuestions.put(ques.getId(), ques);
        int level = Integer.parseInt(ques.getMarks());
        if (level == 1)
            testQuestions1.add(ques);
        if (level == 2)
            testQuestions2.add(ques);
        if (level == 3)
            testQuestions3.add(ques);
        if (level == 4)
            testQuestions4.add(ques);
    }

    public void updateQuestion(String id, QuestionModel question) {

        String[] locate = id.split(",");
        int level = Integer.parseInt(locate[0]);
        int index = Integer.parseInt(locate[1]) - 1;

        if (level == 1)
            testQuestions1.set(index, question);
        if (level == 2)
            testQuestions2.set(index, question);
        if (level == 3)
            testQuestions3.set(index, question);
        if (level == 4)
            testQuestions4.set(index, question);

    }

    public void addLevel(int level, List<QuestionModel> list) {
        if (level == 1)
            testQuestions1.addAll(list);
        if (level == 2)
            testQuestions2.addAll(list);
        if (level == 3)
            testQuestions3.addAll(list);
        if (level == 4)
            testQuestions4.addAll(list);
    }

    public void deleteById(String id) {
        String[] locate = id.split(",");
        int level = Integer.parseInt(locate[0]);
        int index = Integer.parseInt(locate[1]) - 1;
        if (level == 1)
            testQuestions1.remove(index);
        if (level == 2)
            testQuestions2.remove(index);
        if (level == 3)
            testQuestions3.remove(index);
        if (level == 4)
            testQuestions4.remove(index);

    }

    public Question getQuestion(String id) {
        QuestionModel question = new QuestionModel();
        String[] locate = id.split(",");
        int level = Integer.parseInt(locate[0]);
        int index = Integer.parseInt(locate[1]) - 1;

        if (level == 1)
            question = testQuestions1.get(index);
        if (level == 2)
            question = testQuestions2.get(index);
        if (level == 3)
            question = testQuestions3.get(index);
        if (level == 4)
            question = testQuestions4.get(index);

        return question.getQuestion();
    }

    public String getAnswer(String id) {
        String ans = "";
        String[] locate = id.split(",");
        int level = Integer.parseInt(locate[0]);
        int index = Integer.parseInt(locate[1]);

        if (level == 1)
            ans = testQuestions1.get(index).getAns();
        if (level == 2)
            ans = testQuestions2.get(index).getAns();
        if (level == 3)
            ans = testQuestions3.get(index).getAns();
        if (level == 4)
            ans = testQuestions4.get(index).getAns();

        return ans;
    }

    public int getWeight(String id) {
        QuestionModel question = new QuestionModel();
        String[] locate = id.split(",");
        int level = Integer.parseInt(locate[0]);
        int index = Integer.parseInt(locate[1]) - 1;

        if (level == 1)
            question = testQuestions1.get(index);
        if (level == 2)
            question = testQuestions2.get(index);
        if (level == 3)
            question = testQuestions3.get(index);
        if (level == 4)
            question = testQuestions4.get(index);
        return Integer.parseInt(question.getMarks());

    }

    public List<QuestionModel> getAllQuestions() {
        List<QuestionModel> allQues = new ArrayList<>();
        allQues.addAll(testQuestions1);
        allQues.addAll(testQuestions2);
        allQues.addAll(testQuestions3);
        allQues.addAll(testQuestions4);
        return allQues;
    }

}
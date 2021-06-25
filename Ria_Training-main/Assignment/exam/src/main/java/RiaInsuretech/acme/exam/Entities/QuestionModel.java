package RiaInsuretech.acme.exam.Entities;

public class QuestionModel {

    private String id;
    private String question;
    private String options;

    private String ans;

    private String marks;

    public QuestionModel(String question, String options, String ans, String marks) {
        this.question = question;
        this.options = options;
        this.ans = ans;
        this.marks = marks;
    }

    public QuestionModel() {
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public Question getQuestion() {

        String[] ops = options.split(" : ");

        return new Question(id, question, ops[0], ops[1], ops[2], ops[3]);
    }

}

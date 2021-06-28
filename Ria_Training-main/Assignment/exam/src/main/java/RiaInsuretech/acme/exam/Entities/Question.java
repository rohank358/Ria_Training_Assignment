package RiaInsuretech.acme.exam.Entities;

public class Question {
    public String id;
    public String ques;
    public String A;
    public String B;
    public String C;
    public String D;

    public Question(String id, String ques, String A, String B, String C, String D) {
        this.id = "1,1";
        this.ques = ques;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

    }

    public Question() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

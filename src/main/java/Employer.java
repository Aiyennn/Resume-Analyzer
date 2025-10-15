import java.util.ArrayList;

public class Employer extends User{
    ArrayList<JobDescription> jobDescriptions;
    // Save employer and job descriptions

    public Employer(String name, int age, String password, String email, Resume resume) {
        super(name, age, password, email, resume);
    }
}

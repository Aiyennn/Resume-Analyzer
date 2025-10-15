import java.util.ArrayList;

public class Employer extends User{
    ArrayList<JobDescription> jobDescriptions;

    public Employer(String name, String password, String email, ArrayList<JobDescription> jobDescriptions) {
        super(name, password, email);
        this.jobDescriptions = jobDescriptions;
    }

    public Employer() {}
}

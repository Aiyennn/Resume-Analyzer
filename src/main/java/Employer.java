import java.util.ArrayList;

public class Employer extends User{
    private ArrayList<JobDescription> jobDescriptions;

    public Employer(String name, String password, String email, ArrayList<JobDescription> jobDescriptions) {
        super(name, password, email);
        this.jobDescriptions = jobDescriptions;
    }

    public Employer() {}

    public ArrayList<JobDescription> getJobDescriptions() {
        return jobDescriptions;
    }


    public void addJobDescription(JobDescription jobDescription) {
        jobDescriptions.add(jobDescription);
    }

}

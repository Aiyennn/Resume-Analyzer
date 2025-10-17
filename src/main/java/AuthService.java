import java.util.ArrayList;

public class AuthService {
    private Database database;

    public AuthService(Database database) {
        this.database = database;
    }

    public JobSeeker jobSeekerlogin(String email, String password) {

        try {
            JobSeeker user = database.getJobSeekerByEmail(email, password);
            if (user == null) {
                System.out.println("User Not Found");
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JobSeeker jobSeekerRegister(String name, String email, String password) {

        Resume resume = new Resume();
        JobSeeker jobSeeker = new JobSeeker(name, email, password, resume);

        try {

            if (database.jobSeekerExists(email)) {
                System.out.println("⚠\uFE0F User already exists");
                return null;
            }

            database.saveJobSeeker(jobSeeker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobSeeker;
    }

    public Employer employerLogin(String email, String password) {

        try {
            return database.getEmployerByEmail(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public Employer employerRegister(String name, String email, String password) {

        ArrayList<JobDescription> jobDescriptions = new ArrayList<>();
        Employer employer = new Employer(name, email, password, jobDescriptions);

        try {

            if (database.employerExists(email)) {
                System.out.println("User already exists please login");
                return null;
            }

            database.saveEmployer(employer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employer;

    }

}

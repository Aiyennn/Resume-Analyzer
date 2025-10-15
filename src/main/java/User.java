import java.util.Date;

public class User {
    public User(String name, String password, String email, Resume resume) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.resume = resume;
    }

    // For Employer
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.resume = null;
    }

    public User() {}

    protected String name;
    protected String password;
    protected String email;
    protected Resume resume;


    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Resume getResume() {
        return resume;
    }


    public void addSection(ResumeSection resumeSection) {
        resume.addSection(resumeSection);
    }
    
}
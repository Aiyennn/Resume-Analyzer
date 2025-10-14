import java.util.Date;

public class User {
    public User(String name, int age, String password, String email, Resume resume) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.email = email;
        this.resume = resume;
    }

    public User() {}

    protected String name;
    protected int age;
    protected String password;
    protected String email;
    protected Resume resume;


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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
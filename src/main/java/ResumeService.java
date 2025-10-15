import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeService {
    private Database database;
    public ResumeService (Database database) {
        this.database = database;
    }

    public void addSkill (LocalDate start,
                          ArrayList<String> achievements,
                          String skill,
                          String proficiency,
                          JobSeeker user) {
        addSection(new Skill(start, achievements, skill, proficiency), user);
    }

    public void addExperience (LocalDate start,
                               LocalDate end,
                               ArrayList<String> achievements,
                               String role,
                               String company,
                               JobSeeker user) {
        addSection(new Experience(start, end, achievements, role, company), user);
    }

    public void addEducation (LocalDate start,
                              LocalDate end,
                              ArrayList<String> achievements,
                              String degree,
                              String school, JobSeeker user) {
        addSection(new Education(start, end, achievements, degree, school), user);
    }

    private void addSection(ResumeSection section, JobSeeker user) {

        user.addSection(section);

        try {
            database.updateJobSeeker(user);
        } catch (Exception e) {
            System.out.println("Error updating job seeker: " + e.getMessage());
        }
    }
}

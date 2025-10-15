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
        Skill newSkill = new Skill(start, achievements, skill, proficiency);
        user.addSection(newSkill);

        try {
        database.updateJobSeeker(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addExperience (LocalDate start,
                               LocalDate end,
                               ArrayList<String> achievements,
                               String role,
                               String company,
                               User user) {
        Experience newExperience = new Experience(start, end, achievements, role, company);
        user.addSection(newExperience);
    }

    public void addEducation (LocalDate start,
                              LocalDate end,
                              ArrayList<String> achievements,
                              String degree,
                              String school, User user) {
        Education newEducation = new Education(start, end, achievements, degree, school);
        user.addSection(newEducation);
    }
}

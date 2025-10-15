import java.util.ArrayList;

public class JobDescription {
    String title;
    ArrayList<String> skillQualification;
    ArrayList<String> educationQualification;
    ArrayList<String> experienceQualification;

    public JobDescription(String title, ArrayList<String> skillQualification, ArrayList<String> educationQualification, ArrayList<String> experienceQualification) {
        this.title = title;
        this.skillQualification = skillQualification;
        this.educationQualification = educationQualification;
        this.experienceQualification = experienceQualification;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getSkillQualification() {
        return skillQualification;
    }

    public ArrayList<String> getEducationQualification() {
        return educationQualification;
    }

    public ArrayList<String> getExperienceQualification() {
        return experienceQualification;
    }




}

import java.util.ArrayList;
import java.util.List;

public class Resume {

    List<Education> educations = new ArrayList<>();
    List<Experience> experiences = new ArrayList<>();
    List<Skill> skills = new ArrayList<>();

    public Resume(List<Education> educations, List<Experience> experiences, List<Skill> skills) {
        this.educations = educations;
        this.experiences = experiences;
        this.skills = skills;
    }

    // For test case remove
    public Resume() {}

    public List<Education> getEducations() {
        return educations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Skill> getSkills() {
        return skills;
    }


    // Switch arrow case not supported
    public void addSection (ResumeSection section) {
        if (section instanceof Education education) {
            educations.add(education);
        } else if (section instanceof Experience experience) {
            experiences.add(experience);
        } else if (section instanceof Skill skill) {
            skills.add(skill);
        } else {
            throw new IllegalArgumentException("Unknown ResumeSection type: " + section.getClass());
        }
    }

}

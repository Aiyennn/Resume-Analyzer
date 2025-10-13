import java.util.ArrayList;

public class Resume {
    ArrayList<Education> educations = new ArrayList<>();
    ArrayList<Experience> experiences = new ArrayList<>();
    ArrayList<Skill> skills = new ArrayList<>();

    public ArrayList<Education> getEducations() {
        return educations;
    }

    public ArrayList<Experience> getExperiences() {
        return experiences;
    }

    public ArrayList<Skill> getSkills() {
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

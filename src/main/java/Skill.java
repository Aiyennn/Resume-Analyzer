import java.time.LocalDate;
import java.util.ArrayList;

public class Skill extends ResumeSection{

    // Credentials
    private String skill;
    private String proficiency;

    public String getSkill() {
        return skill;
    }

    public String getProficiency() {
        return proficiency;
    }

    public Skill(LocalDate acquiredDate, ArrayList<String> achievements, String skill, String proficiency) {
        super(acquiredDate, LocalDate.now(), achievements);
        this.skill = skill;
        this.proficiency = proficiency;
    }
}

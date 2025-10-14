import java.time.LocalDate;
import java.util.ArrayList;

public class Education extends ResumeSection {

    // Credentials
    private String degree;
    private String school;

    public Education(LocalDate startDate, LocalDate endDate,ArrayList<String> achievements, String degree, String school) {
        super(startDate, endDate, achievements);
        this.degree = degree;
        this.school = school;
        this.achievements = new ArrayList<>();
    }

    public String getDegree() {
        return degree;
    }

    public String getSchool() {
        return school;
    }


}

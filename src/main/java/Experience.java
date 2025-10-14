import java.time.LocalDate;
import java.util.ArrayList;

public class Experience extends ResumeSection{
    public String getRole() {
        return role;
    }

    public String getCompany() {
        return company;
    }

    // Credentials
    private String role;
    private String company;

    public Experience(LocalDate startDate, LocalDate endDate, ArrayList<String> achievements, String role, String company) {
        super(startDate, endDate, achievements);
        this.role = role;
        this.company = company;
    }
}

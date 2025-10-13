import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ResumeSection {
    LocalDate startDate;
    LocalDate endDate;
    ArrayList<String> achievements;

    public ResumeSection(LocalDate startDate, LocalDate endDate, ArrayList<String> achievements) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.achievements = achievements;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

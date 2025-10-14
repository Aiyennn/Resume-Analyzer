import java.time.LocalDate;
import java.util.ArrayList;

public interface Analyzers {

    public double dateRelevance(LocalDate startDate, LocalDate endDate);

    public double credentialRelevance(String credential1, String credential2);

    public double achievementRelevance(ArrayList<String> achievements);

    public double analyze(ResumeSection resumeSection);

}

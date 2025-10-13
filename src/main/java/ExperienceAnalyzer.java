import java.time.LocalDate;
import java.util.ArrayList;

public class ExperienceAnalyzer implements Analyzers{


    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {

        // Step 1: Receive input
        // - startDate
        // - endDate
        // - longer the better

        // Step 3: Calculate actual duration
        // actualDurationDays = days between startDate and endDate

        // Step 4: Assign points based on timeliness
        // - +5 1 year
        // - +10 10 years
        // - +99999999999 95 years


        // Step 6: Return the points

        return 0;
    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {

        // Step 1: Receive input
        // credential1: the professional position (e.g., "Manager", "Engineer", "Intern")
        // credential2: the name or tier of the organization (e.g., "Google", "Startup", "Local Business")

        // kayo bahala sa points
        // Manager = 2pts etc

        // totalPoints = credential1 + credential2
        // return total points

        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Receive input
        // achievements: a list of academic recognitions
        // Kayo mag decide ng points
        // Ano mga pwedeng achievement sa experience?
        // Example: ["Employee of the month", "Trespassing", "Scammer"]

        // return points
        return 0;
    }

    @Override
    public double aggregatePoints(ResumeSection resumeSection) {
        return 0;
    }
}

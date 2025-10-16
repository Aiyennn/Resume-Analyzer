import java.time.LocalDate;
import java.util.ArrayList;

public class ExperienceAnalyzer implements Analyzers{

    JobKeywords job;

    public ExperienceAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }


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

        // Step 2: Match input position with job.experiencePositions[] and assign job.positionPoints[] value
        // Step 3: Match input company with job.experienceCompanies[] and assign job.companyPoints[] value
        // Step 4: Match input achievement with job.experienceAchievements[] and assign job.experienceAchievementPoints[] value
        // Step 5: Sum all assigned values for total experience score


        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Take ArrayList<String> of input achievements
        // Step 2: Compare each entry with job.experienceAchievements[]
        // Step 3: For every match, add corresponding job.experienceAchievementPoints[] value
        // Step 4: Return the total points


        // return points
        return 0;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {

        Util.printBanner("Experience: " + 0);
        return 0;
    }
}

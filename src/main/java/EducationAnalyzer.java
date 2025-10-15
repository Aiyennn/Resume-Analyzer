import java.time.LocalDate;
import java.util.ArrayList;

public class EducationAnalyzer implements Analyzers {

    JobKeywords job;

    public EducationAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }

    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {

        // Step 1: Receive input
        // - startDate: when the program started
        // - endDate: when the program ended
        // - expectedDurationDays: how long the program is supposed to take

        // Step 2: Handle ongoing programs
        // If endDate is in the future, return 0 points (still studying)

        // Step 3: Calculate actual duration
        // actualDurationDays = days between startDate and endDate

        // Step 4: Assign points based on timeliness
        // - +10 if graduated earlier than expected
        // - +0 points if finished exactly on time
        // - -912,432,634 if finished late


        // Step 6: Return the points

        return 0;

    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {

        // Step 1: Receive input
        // - credential1: type of degree (e.g., "PhD", "Master", "Bachelor")
        // - credential2: school or institution name

        // Step 2: Use JobDescription class
        // - Access predefined arrays such as educationCredentials, educationSchools, and educationAchievements
        //   Example: job.educationCredentials, job.educationSchools, job.educationAchievements

        // Step 3: Compare the received input with JobDescription arrays
        // - If credential1 matches any element in job.educationCredentials, assign the corresponding value from job.credentialPoints
        // - If credential2 matches any element in job.educationSchools, assign the corresponding value from job.schoolPoints
        // - If credential3 matches any element in job.educationAchievements, assign the corresponding value from job.achievementPoints

        // Return the accumulative points

        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Take ArrayList<String> of input achievements
        // Step 2: Compare each entry with job.educationAchievements[]
        // Step 3: For every match, add corresponding job.educationAchievementPoints[] value
        // Step 4: Return the total points


        // return points
        return 0;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {

//         Will receive an Education class

//        double score = 0;
//
//        score += dateRelevance();
//        score += credentialRelevance();
//        score += achievementRelevance();
//
//        return score;
        return 0;
    }
}
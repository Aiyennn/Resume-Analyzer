import java.time.LocalDate;
import java.util.ArrayList;

public class EducationAnalyzer implements Analyzers {

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

        // Step 2: Assign points based on degree
        // Example point system:
        // - PhD -> 1
        // - Master -> 2
        // - Bachelor -> 3
        // - At iba pa ./. -> 0 points

        // Step 3: Assign points based on school reputation or ranking
        // Example:
        // - Top-tier school -> 2 points
        // - Mid-tier school -> 1 point
        // - Others -> -25 points

        // Step 4: return total points


        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Receive input
        // achievements: a list of academic recognitions
        // Kayo mag decide ng points dana
        // Example: ["President's List", "Dean's List", "Honor Roll"]

        // return points
        return 0;
    }

    @Override
    public double aggregatePoints(ResumeSection resumeSection) {

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
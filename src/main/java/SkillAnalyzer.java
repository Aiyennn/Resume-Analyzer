import java.time.LocalDate;
import java.util.ArrayList;

public class SkillAnalyzer implements Analyzers{


    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {

        // Step 1: Receive input
        // - startDate: when the started using skills
        // - endDate: current date today


        // Step 2: Calculate points longer = better

        // Step 4: Assign points based on timeliness
        // - +10 points = 5 years etc


        // Step 6: Return the points
        return 0;
    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {

        // Step 1: Receive input
        // - proficiency: level of expertise (e.g., "Expert", "Intermediate", "Beginner")
        // - skill: type of skill or technology (e.g., "Java", "Python", "Excel")

        // Step 2: Assign points
        // - Expert -> 3 points
        // - Intermediate -> 2 points
        // - Beginner -> 1 point
        // - Unknown / unrecognized -> 0 points

        // Step 3: Assign points based on skill relevance
        // - High-demand skill -> 2 points
        // - Medium-demand skill -> 1 point
        // - Low-demand / generic skill -> 0 points


        // return points
        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Receive input
        // achievements: a list of skill certificates or recognitions
        // Di ko alam pano to | Suggest kayo
        // Example: ["Certified Java Developer", "Completed Online Training"]

        return 0;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {
        return 0;
    }
    // Analyze the entire resume give a score and grade it Needs improvement, Average, Strong, Excellent
}

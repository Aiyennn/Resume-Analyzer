import java.time.LocalDate;
import java.util.ArrayList;

public class SkillAnalyzer implements Analyzers{

    JobKeywords job;

    public SkillAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }

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

        // Step 2: Match input proficiency with job.skillProficiencies[] and assign job.proficiencyPoints[] value
        // Step 3: Match input technology with job.skillTechnologies[] and assign job.technologyPoints[] value
        // Step 4: Sum both for total skill score


        // return points

        return 0;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        // Step 1: Take ArrayList<String> of input achievements
        // Step 2: Compare each entry with job.skillAchievements[]
        // Step 3: For every match, add corresponding job.skillAchievementPoints[] value
        // Step 4: Return the total points

        return 0;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {
        return 0;
    }
    // Analyze the entire resume give a score and grade it Needs improvement, Average, Strong, Excellent
}

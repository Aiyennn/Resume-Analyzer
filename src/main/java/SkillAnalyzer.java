import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SkillAnalyzer implements Analyzers{

    JobKeywords job;

    public SkillAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }

    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {


        double score = 0;
        // Step 1: Receive input
        // - startDate: when the started using skills
        // - endDate: current date today

        long totalDays =  ChronoUnit.DAYS.between(startDate, endDate);
        // Step 2: Calculate points longer = better


        if(totalDays/365 <= 1 && totalDays/365 > 0){
            score += 5;
        }else if(totalDays/365 <= 5){
            score += 10;
        }else{
            score += 999;
        }

        // Step 4: Assign points based on timeliness
        // - +10 points = 5 years etc


        // Step 6: Return the points
        return score;
    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {

        double score = 0;
        // Step 1: Receive input
        // - proficiency: level of expertise (e.g., "Expert", "Intermediate", "Beginner")
        // - skill: type of skill or technology (e.g., "Java", "Python", "Excel")

        for (int i = 0; i < job.skillProficiencies.length; i++) {
            if (credential1.equalsIgnoreCase(job.skillProficiencies[i])) {
                score += job.proficiencyPoints[i];
                break;
            }
        }

        // Step 2: Match input proficiency with job.skillProficiencies[] and assign job.proficiencyPoints[] value

        for (int i = 0; i < job.skillTechnologies.length; i++) {
            if (credential2.equalsIgnoreCase(job.skillTechnologies[i])) {
                score += job.technologyPoints[i];
                break;
            }
        }
        // Step 3: Match input technology with job.skillTechnologies[] and assign job.technologyPoints[] value
        // Step 4: Sum both for total skill score


        // return points

        return score;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        double score = 0;
        // Step 1: Take ArrayList<String> of input achievements

        for(int i = 0; i < achievements.size(); i++){
            for(int j = 0; j< job.experienceAchievements.length; j++){
                if(job.skillAchievements[j].equalsIgnoreCase(achievements.get(i))){
                    score += job.skillAchievementPoints[j];
                    break;
                }
            }
        }
        // Step 2: Compare each entry with job.skillAchievements[]
        // Step 3: For every match, add corresponding job.skillAchievementPoints[] value
        // Step 4: Return the total points

        return score;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {

        Skill skillResume = (Skill) resumeSection;

        LocalDate startDate = skillResume.getStartDate();
        LocalDate endDate = skillResume.getEndDate();

        String credential1 = skillResume.getProficiency();
        String credential2 = skillResume.getSkill();

        ArrayList<String> achievements = skillResume.getAchievements();

        double score = 0;

        score += dateRelevance(startDate, endDate);
        score += credentialRelevance(credential1, credential2);
        score += achievementRelevance(achievements);

        Util.printBanner("Skill: " + score);
        return score;
    }
    // Analyze the entire resume give a score and grade it Needs improvement, Average, Strong, Excellent
}

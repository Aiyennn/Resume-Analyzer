import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ExperienceAnalyzer implements Analyzers{

    JobKeywords job;

    public ExperienceAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }


    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {

        double score = 0;

        // Step 1: Receive input
        // - startDate
        // - endDate
        // - longer the better

        long totalDays =  ChronoUnit.DAYS.between(startDate, endDate);

        // Step 3: Calculate actual duration
        // actualDurationDays = days between startDate and endDate

        // Step 4: Assign points based on timeliness
        // - +5 1 year
        // - +10 10 years
        // - +99999999999 95 years

        if(totalDays/365 <= 1 && totalDays/365 > 0){
            score += 5;
        }else if(totalDays/365 == 10){
            score += 10;
        }else{
            score += 999;
        }

        // Step 6: Return the points

        return score;
    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {
        double score = 0;

        // Step 1: Receive input
        // credential1: the professional position (e.g., "Manager", "Engineer", "Intern")
        // credential2: the name or tier of the organization (e.g., "Google", "Startup", "Local Business")

         for (int i = 0; i < job.experiencePositions.length; i++) {
            if (credential1.equalsIgnoreCase(job.experiencePositions[i])) {
                score += job.positionPoints[i];
                break;
            }
        }
        // Step 2: Match input position with job.experiencePositions[] and assign job.positionPoints[] value

        for (int i = 0; i < job.experienceCompanies.length; i++) {
            if (credential2.equalsIgnoreCase(job.experienceCompanies[i])) {
                score += job.companyPoints[i];
                break;
            }
        }
        // Step 3: Match input company with job.experienceCompanies[] and assign job.companyPoints[] value
        // Step 5: Sum all assigned values for total experience score
        return score;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        double score = 0;
        // Step 1: Take ArrayList<String> of input achievements

        for(int i = 0; i < achievements.size(); i++){
            for(int j = 0; j< job.experienceAchievements.length; j++){
                if(job.experienceAchievements[j].equalsIgnoreCase(achievements.get(i))){
                    score += job.experienceAchievementPoints[j];
                    break;
                }
            }
        }
        // Step 2: Compare each entry with job.experienceAchievements[]
        // Step 3: For every match, add corresponding job.experienceAchievementPoints[] value
        // Step 4: Return the total points


        // return points
        return score;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {

        Experience experienceResume = (Experience) resumeSection;

        LocalDate startDate = experienceResume.getStartDate();
        LocalDate endDate = experienceResume.getEndDate();

        String credential1 = experienceResume.getRole();
        String credential2 = experienceResume.getCompany();

        ArrayList<String> achievements = experienceResume.getAchievements();

        double score = 0;

        score += dateRelevance(startDate, endDate);
        score += credentialRelevance(credential1, credential2);
        score += achievementRelevance(achievements);

        Util.printBanner("Experience: " + score);
        return score;
    }
}

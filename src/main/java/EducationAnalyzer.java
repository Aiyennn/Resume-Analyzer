import java.time.LocalDate;
import java.util.ArrayList;

public class EducationAnalyzer implements Analyzers {

    JobKeywords job;

    public EducationAnalyzer(JobKeywords jobKeywords) {
        this.job = jobKeywords;
    }

    @Override
    public double dateRelevance(LocalDate startDate, LocalDate endDate) {

        // Still studying
        if (endDate.isAfter(LocalDate.now())) {
            return 0;
        }

        // Step 3: Calculate actual duration
        long actualDurationDays = endDate.toEpochDay() - startDate.toEpochDay();

        // Expected duration
        long expectedDurationDays = 1460;

        // Step 4: Assign points based on timeliness
        if (actualDurationDays < expectedDurationDays) {
            // Finished earlier than expected
            return 10;
        } else if (actualDurationDays == expectedDurationDays) {
            // Finished exactly on time
            return 0;
        } else {
            // Finished late
            return -5;
        }

    }

    @Override
    public double credentialRelevance(String credential1, String credential2) {

        double totalPoints = 0;

        for (int i = 0; i < job.educationCredentials.length; i++) {
            if (credential1.equalsIgnoreCase(job.educationCredentials[i])) {
                totalPoints += job.credentialPoints[i];
                break;
            }
        }

        for (int i = 0; i < job.educationSchools.length; i++) {
            if (credential2.equalsIgnoreCase(job.educationSchools[i])) {
                totalPoints += job.schoolPoints[i];
                break;
            }
        }


        return totalPoints;
    }

    @Override
    public double achievementRelevance(ArrayList<String> achievements) {

        double totalPoints = 0;

        for (int i = 0; i < achievements.size(); i++) {
            String inputAchievement = achievements.get(i);

            for (int j = 0; j < job.educationAchievements.length; j++) {
                if (inputAchievement.equalsIgnoreCase(job.educationAchievements[j])) {
                    totalPoints += job.achievementPoints[j];
                    break;
                }
            }
        }

        return totalPoints;
    }

    @Override
    public double analyze(ResumeSection resumeSection) {

        Education educationResume = (Education) resumeSection;

        LocalDate startDate = educationResume.getStartDate();
        LocalDate endDate = educationResume.getEndDate();

        String credential1 = educationResume.getDegree();
        String credential2 = educationResume.getSchool();

        ArrayList<String> achievements = educationResume.getAchievements();

        double score = 0;

        score += dateRelevance(startDate, endDate);
        score += credentialRelevance(credential1, credential2);
        score += achievementRelevance(achievements);

        Util.printBanner("Education " + score);

        return score;

    }
}
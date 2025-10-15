import java.util.ArrayList;

public class MatchJobService {
    private Database database;
    private ArrayList<JobDescription> jobListing;
    private EducationAnalyzer educationAnalyzer;
    private ExperienceAnalyzer experienceAnalyzer;
    private SkillAnalyzer skillAnalyzer;

    public MatchJobService(Database database, EducationAnalyzer educationAnalyzer, ExperienceAnalyzer experienceAnalyzer, SkillAnalyzer skillAnalyzer) {
        this.database = database;
    }

    public void postJob(ArrayList<String> skillQualifications,
                        ArrayList<String> educationQualification,
                        ArrayList<String> experienceQualification,
                        String title, Employer user) {

        JobDescription jobDescription = new JobDescription(
                title,
                skillQualifications,
                educationQualification,
                experienceQualification
        );

        user.addJobDescription(jobDescription);
        database.updateEmployer(user);

        // Instantiate
        // Save to db

    }

}

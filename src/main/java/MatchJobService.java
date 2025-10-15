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

    public void postJob(ArrayList<Skill> skillQualifications,
                        ArrayList<Education> educationQualification,
                        ArrayList<Experience> experienceQualification,
                        String title) {

        // Instantiate
        // Save to db

    }

    //
}

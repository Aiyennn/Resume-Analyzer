import java.util.ArrayList;

public class MatchJobService {
    private Database database;
    private ArrayList<JobDescription> jobListing;

    public MatchJobService(Database database) {
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

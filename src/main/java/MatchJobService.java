import java.util.ArrayList;
import java.util.List;

public class MatchJobService {
    private Database database;
    private List<JobDescription> jobListing;
    private EducationAnalyzer educationAnalyzer;
    private ExperienceAnalyzer experienceAnalyzer;
    private SkillAnalyzer skillAnalyzer;

    public MatchJobService(Database database, EducationAnalyzer educationAnalyzer, ExperienceAnalyzer experienceAnalyzer, SkillAnalyzer skillAnalyzer) {

        this.database = database;
        this.educationAnalyzer = educationAnalyzer;
        this.experienceAnalyzer = experienceAnalyzer;
        this.skillAnalyzer = skillAnalyzer;

        jobListing = database.parseJobDescriptions();

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

    public List<Double> analyzeResume(Resume resume) {

        List<Education> educations = resume.getEducations();
        List<Skill> skills = resume.getSkills();
        List<Experience> experiences = resume.getExperiences();
        List<Double> scores = new ArrayList<>();

        double educationScore = 0;
        double skillScore = 0;
        double experienceScore = 0;

        for (Education education : educations) {
            educationScore += educationAnalyzer.analyze(education);
        }

        for (Skill skill: skills) {
            skillScore += skillAnalyzer.analyze(skill);
        }

        for (Experience experience: experiences) {
            experienceScore += experienceAnalyzer.analyze(experience);
        }

        scores.add(educationScore);
        scores.add(skillScore);
        scores.add(experienceScore);

        return scores;

    }

    public List<JobDescription> getJobListing() {
        return jobListing;
    }

}

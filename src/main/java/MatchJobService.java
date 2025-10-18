import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//        /!\ For debugging
//        System.out.println("Job listing have been parse with: " + jobListing.size());
//
//        for (JobDescription jobDescription : jobListing) {
//            System.out.println(jobDescription.getTitle());
//        }

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
        System.out.println("Employer updated: " + user.getName() + user.getJobDescriptions());
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

    public Map<JobDescription, Integer> getMatchJobs(Resume resume) {

        // Match keywords from SKill, Education, and Experience from resume to each of Job Description
        // Keywords from SKill => skill | Education => Degree | Company => Role


        // System.out.println("MatchJobService.getMatchJobs() called"); | For debugging

        Map<JobDescription, Integer> matchJobs = new HashMap<>();
        List<Skill> skills = resume.getSkills();
        List<Experience> experiences = resume.getExperiences();

        System.out.println(jobListing.size());
        for (JobDescription job : jobListing) {

            System.out.println("Checking: " + job.getTitle());
            int points = 0;

            // Check skills
            for (String skillQualification : job.getSkillQualification()) {

                for (Skill skill : skills) {
                    if (skill.getSkill().equalsIgnoreCase(skillQualification)) {
                        points++;
                    }

                    System.out.println(skill.getSkill() + " " + skillQualification);
                }

            }

            // Check experience
            for (String experienceQualification : job.getExperienceQualification()) {

                for (Experience experience : experiences) {
                    if (experience.getRole().equalsIgnoreCase(experienceQualification)) {
                        points++;
                    }

                    System.out.println(experience.getRole() + " " + experienceQualification);
                }

            }

            matchJobs.put(job, points);

        }

        return matchJobs;


    }

    public List<JobDescription> getJobListing() {
        return jobListing;
    }

}

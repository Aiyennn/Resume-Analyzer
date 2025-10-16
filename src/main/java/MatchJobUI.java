import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchJobUI {

    private MatchJobService matchJobService;
    private Scanner scanner;

    public MatchJobUI(MatchJobService matchJobService, Scanner scanner) {
        this.matchJobService = matchJobService;
        this.scanner = scanner;
    }

    public void displayMenu(User user) {

    }

    public void displayMatchJobs(){
        // Match by keyword
    }

    public void displayAllJobs(){
        // Print shit
    }

    public void analyzeResume(JobSeeker user) {

        // Resume resume = user.getResume()

        // SKill skill = resume.getSkill() etc

        matchJobService.analyzeResume();

    }


//    Employer Section ------------------------------------------------------------------------------

    public void inputPostJob(Employer user) {


        System.out.print("Input title for the job: ");
        String title = scanner.nextLine();
        ArrayList<String> skillQualifications = inputQualifications("Skill");
        ArrayList<String> educationQualification = inputQualifications("Education");
        ArrayList<String> experienceQualification = inputQualifications("Experience");

        matchJobService.postJob(skillQualifications, educationQualification, experienceQualification, title, user);

    }

    public ArrayList<String> inputQualifications(String type) {

        ArrayList<String> qualifications = new ArrayList<>();
        System.out.println("Enter " + qualifications + " requirements (type 'done' to finish):");
        while (true) {
            System.out.print("> ");
            String achievement = scanner.nextLine().trim();
            if (achievement.equalsIgnoreCase("done")) {
                break;
            } else if (!achievement.isEmpty()) {
                qualifications.add(achievement);
            } else {
                System.out.println("Please enter a valid text or 'done' to stop.");
            }
        }

        return qualifications;
    }


    public void displayPostedJobs(Employer employer) {

        System.out.println("I'm here");
        ArrayList<JobDescription> postedJobs = employer.getJobDescriptions();

        for (JobDescription jobDescription : postedJobs) {
            Util.printBanner(jobDescription.getTitle());
            System.out.println("Skill Requirements: " + jobDescription.getSkillQualification());
            System.out.println("Experience Requirements: " + jobDescription.getExperienceQualification());
            System.out.println("Education Requirements: " + jobDescription.getEducationQualification());
        }

    }
}

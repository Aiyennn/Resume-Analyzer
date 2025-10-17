import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchJobUI {

    private MatchJobService matchJobService;
    private Scanner scanner;

    public MatchJobUI(MatchJobService matchJobService, Scanner scanner) {
        this.matchJobService = matchJobService;
        this.scanner = scanner;
    }

    public void displayMenu(JobSeeker user) {

        Util.printBanner("Matchjob");
        System.out.println("[1] Display All Jobs");
        System.out.println("[2] Display Match Jobs");
        System.out.println("[3] Analyze My Resume");

        System.out.print("Select choice");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                displayAllJobs();
                break;
            case "2":
                displayMatchJobs();
                break;
            case "3":
                analyzeResume(user);
                break;
        }

    }

    public void displayMatchJobs(){
        // Match by keyword
    }

    public void displayAllJobs(){

        System.out.println("Im here");
        List<JobDescription> allJobs = matchJobService.getJobListing();

        for (JobDescription job: allJobs) {
            Util.printBanner(job.getTitle());
            System.out.println("Skill: " + job.getSkillQualification());
            System.out.println("Experience: " + job.getExperienceQualification());
            System.out.println("Education: " + job.getEducationQualification());
        }
    }

    public void analyzeResume(JobSeeker user) {

        Resume resume = user.getResume();
        List<Double> scores = matchJobService.analyzeResume(resume);

        gradeResume(scores);

    }

    public void gradeResume(List<Double> scores){
        String[] sectionName = {"Education", "Skill", "Experience"};
        String[] remarks = {"Needs Improvement", "Average", "Strong", "Excellent"};

        System.out.println();
        System.out.println("========== SCORE REPORT ==========\n");

        for (int i = 0; i < scores.size(); i++) {
            double score = scores.get(i);
            String remark;

            if (score <= 10) {
                remark = remarks[0];
            } else if (score <= 20) {
                remark = remarks[1];
            } else if (score <= 30) {
                remark = remarks[2];
            } else {
                remark = remarks[3];
            }

            System.out.printf("%-15s: %s%n", "Section", sectionName[i]);
            System.out.printf("%-15s: %.2f%n", "Score", score);
            System.out.printf("%-15s: %s%n", "Remark", remark);
            System.out.println("-----------------------------------");
        }
        System.out.println("============ END REPORT ===========");
        System.out.println();

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
        System.out.println("Enter " + type + " requirements (type 'done' to finish):");
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

        Util.printBanner("Posted Jobs");
        ArrayList<JobDescription> postedJobs = employer.getJobDescriptions();

        for (JobDescription jobDescription : postedJobs) {
            Util.printBanner(jobDescription.getTitle());
            System.out.println("Skill Requirements: " + jobDescription.getSkillQualification());
            System.out.println("Experience Requirements: " + jobDescription.getExperienceQualification());
            System.out.println("Education Requirements: " + jobDescription.getEducationQualification());
        }

    }
}

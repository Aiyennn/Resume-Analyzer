import java.lang.reflect.Array;
import java.util.*;

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
                displayMatchJobs(user);
                break;
            case "3":
                analyzeResume(user);
                break;
        }

    }

    public void displayMatchJobs(JobSeeker user){

        Resume resume = user.getResume();

        Map<JobDescription, Integer> getMatchJobs = matchJobService.getMatchJobs(resume);

        System.out.println("Matched jobs count: " + getMatchJobs.size());

        List<Map.Entry<JobDescription, Integer>> sortedJobs = getMatchJobs.entrySet()
                .stream()
                .sorted(Map.Entry.<JobDescription, Integer>comparingByValue(Comparator.reverseOrder()))
                .toList();

        int num = 1;
        for (Map.Entry<JobDescription, Integer> entry : sortedJobs) {
            Util.printBanner("[" + num + "]: " + entry.getValue() + "Points " + entry.getKey().getTitle());
        }


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

        for (Double score : scores) {
            System.out.println(score);
        }

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

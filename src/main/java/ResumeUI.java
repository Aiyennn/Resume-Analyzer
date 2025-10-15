import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ResumeUI {
    private ResumeService resumeService;
    private Scanner scanner;

    public ResumeUI(ResumeService resumeService, Scanner scanner) {
        this.resumeService = resumeService;
        this.scanner = scanner;
    }

    public void displayMenu(JobSeeker user) {

        System.out.println("1. Input Skills");
        System.out.println("2. Input Experience");
        System.out.println("3. Input Education");
        System.out.println("4. Display Resume");
        System.out.println("5. Return");

        System.out.print("Select a choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                inputSkills(user);
                break;
            case "2":
                inputExperience(user);
                break;
            case "3":
                inputEducation(user);
                break;
            case "4":
                displayResume(user);
                break;
            default:
                System.out.println("Wrong input");
        }

    }

    public void inputSkills(JobSeeker user) {

        LocalDate startDate;
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            startDate = LocalDate.now();
        }



        ArrayList<String> achievements = new ArrayList<>();
        System.out.println("Enter your achievements (type 'done' to finish):");
        while (true) {
            System.out.print("> ");
            String achievement = scanner.nextLine().trim();
            if (achievement.equalsIgnoreCase("done")) {
                break;
            } else if (!achievement.isEmpty()) {
                achievements.add(achievement);
            } else {
                System.out.println("Please enter a valid text or 'done' to stop.");
            }
        }

        String skill = "";
        while (skill.isEmpty()) {
            System.out.print("Enter a skill: ");
            skill = scanner.nextLine().trim();
            if (skill.isEmpty()) {
                System.out.println("⚠️ Skill cannot be empty.");
            }
        }

        String proficiency = "";
        while (proficiency.isEmpty()) {
            System.out.print("Enter your proficiency level (e.g., Beginner, Intermediate, Expert): ");
            proficiency = scanner.nextLine().trim();
            if (proficiency.isEmpty()) {
                System.out.println("⚠️ Proficiency cannot be empty.");
            }
        }

        resumeService.addSkill(startDate, achievements, skill, proficiency, user);

//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate startDate
//         ArrayList<String> achievements
//         String skill
//         String proficiency

//        resumeService.addSkill(startDate, achievements, skill, proficiency, user);

    }

    public void inputExperience(User user) {

//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate Start date
//         LocaleDate End date
//         ArrayList<String> achievements
//         String role
//         String company

//        resumeService.addExperience(startDate, endDate, achievements, role, company, user);

    }

    public void inputEducation(User user) {

//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate Start date
//         LocaleDate End date
//         ArrayList<String> achievements
//         String degree
//         String school

//        resumeService.addExperience(startDate, endDate, achievements, degree, school, user);

    }

    public void displayResume(User user) {

        // Later

    }

}

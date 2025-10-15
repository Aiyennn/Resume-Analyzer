import com.sun.security.jgss.GSSUtil;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        ArrayList<String> achievements = inputAchivements();
        String skill = inputCredential("skill");
        String proficiency = inputCredential("proficiency");

        resumeService.addSkill(startDate, achievements, skill, proficiency, user);

    }

    public void inputExperience(JobSeeker user) {

        LocalDate startDate;
        LocalDate endDate;

        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
            System.out.println("Enter end date (2003-10-30): ");
            endDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            startDate = LocalDate.now();
            endDate = LocalDate.now();
        }

        ArrayList<String> achievements = inputAchivements();
        String role = inputCredential("role");
        String company = inputCredential("company");

        resumeService.addExperience(startDate, endDate, achievements, role, company, user);

    }

    public void inputEducation(JobSeeker user) {

        LocalDate startDate;
        LocalDate endDate;
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
            System.out.println("Enter end date (2003-10-30): ");
            endDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            startDate = LocalDate.now();
            endDate = LocalDate.now();
        }

        ArrayList<String> achievements = inputAchivements();
        String degree = inputCredential("degree");
        String school = inputCredential("school");

        resumeService.addEducation(startDate, endDate, achievements, degree, school, user);

    }

    public void displayResume(User user) {

        Resume resume = user.getResume();
        List<Education> educations = resume.getEducations();
        List<Skill> skills = resume.getSkills();
        List<Experience> experiences = resume.getExperiences();

        Util.displaySection(educations);
        Util.displaySection(skills);
        Util.displaySection(experiences);


    }

    private ArrayList<String> inputAchivements() {

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

        return achievements;
    }

    private String inputCredential(String type) {

        String credential = "";
        while (credential.isEmpty()) {
            System.out.print("Enter a " + type + ": ");
            credential = scanner.nextLine().trim();
            if (credential.isEmpty()) {
                System.out.println("⚠️ " + type + " cannot be empty.");
            }
        }

        return credential;
    }

}

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

        while (true) {

        Util.printBanner("Resume");
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
            case "5":
                return;
            default:
                System.out.println("--Input Error Try Again--");
        }
        }

    }

    public void inputSkills(JobSeeker user) {

//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate startDate
//         ArrayList<String> achievements
//         String skill
//         String proficiency

//        resumeService.addSkill(startDate, achievements, skill, proficiency, user);

        LocalDate startDate;
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Invalid date. Defaulting to today's date.");
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
            System.out.print("Enter your skill (e.g python, javascript, c++): ");
            skill = scanner.nextLine().trim();
            if (skill.isEmpty()) {
                System.out.println("⚠️ skill cannot be empty.");
            }
        }

        String proficiency = "";
        while (proficiency.isEmpty()) {
            System.out.print("Enter proficiency (eg. beginner, intermediate, expert): ");
            proficiency = scanner.nextLine().trim();
            if (proficiency.isEmpty()) {
                System.out.println("⚠️ proficiency cannot be empty.");
            }
        }

        resumeService.addSkill(startDate, achievements, skill, proficiency, user);



    }

    public void inputExperience(JobSeeker user) {


//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate Start date
//         LocaleDate End date
//         ArrayList<String> achievements
//         String role
//         String company

//        resumeService.addExperience(startDate, endDate, achievements, role, company, user);

        LocalDate startDate;
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Invalid date. Defaulting to today's date.");
            startDate = LocalDate.now();
        }

        LocalDate endDate;
        try {
            System.out.print("Enter end date (YYYY-MM-DD): ");
            endDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Invalid date. Defaulting to today's date.");
            endDate = LocalDate.now();
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

        String role = "";
        while (role.isEmpty()) {
            System.out.print("Enter your role (e.g software engineer, data analyst): ");
            role = scanner.nextLine().trim();
            if (role.isEmpty()) {
                System.out.println("⚠️ Role cannot be empty.");
            }
        }

        String company = "";
        while (company.isEmpty()) {
            System.out.print("Enter company name (e.g google, meta, etc): ");
            company = scanner.nextLine().trim();
            if (company.isEmpty()) {
                System.out.println("⚠️ Company name cannot be empty.");
            }
        }

        resumeService.addExperience(startDate, endDate, achievements, role, company, user);

    }

    public void inputEducation(JobSeeker user) {

//         Prompt the user for:  /!\ Ensure that the input are correct and avoid crashing when unexpected input
//         LocaleDate Start date
//         LocaleDate End date
//         ArrayList<String> achievements
//         String degree
//         String school

//        resumeService.addExperience(startDate, endDate, achievements, degree, school, user);

        LocalDate startDate;
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Invalid date. Defaulting to today's date.");
            startDate = LocalDate.now();
        }

        LocalDate endDate;
        try {
            System.out.print("Enter end date (YYYY-MM-DD): ");
            endDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("⚠️ Invalid date. Defaulting to today's date.");
            endDate = LocalDate.now();
        }

        ArrayList<String> achievements = new ArrayList<>();
        System.out.println("Enter your academic achievements (type 'done' to finish):");
        while (true) {
            System.out.print("> ");
            String achievement = scanner.nextLine().trim();
            if (achievement.equalsIgnoreCase("done")) {
                break;
            } else if (!achievement.isEmpty()) {
                System.out.println("Achievement added: " + achievement);
                achievements.add(achievement);
            } else {
                System.out.println("Please enter a valid text or 'done' to stop.");
            }
        }

        String degree = "";
        while (degree.isEmpty()) {
            System.out.print("Enter your degree (e.g., bachelor's degree, master's degree): ");
            degree = scanner.nextLine().trim();
            if (degree.isEmpty()) {
                System.out.println("⚠️ Degree cannot be empty.");
            }
        }

        String school = "";
        while (school.isEmpty()) {
            System.out.print("Enter your school name: (e.g holy angel university, mit, harvard): ");
            school = scanner.nextLine().trim();
            if (school.isEmpty()) {
                System.out.println("⚠️ School name cannot be empty.");
            }
        }

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


}
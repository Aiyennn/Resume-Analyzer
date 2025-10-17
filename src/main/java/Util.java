import java.util.List;

public class Util {

    public static void displaySection(List<? extends ResumeSection> sections) {

        for (ResumeSection section : sections) {

            String credential1 = "";
            String credential2 = "";
            String credential1Title = "";
            String credential2Title = "";

            if (section instanceof Education) {
                credential1 = ((Education) section).getDegree();
                credential2 = ((Education) section).getSchool();
                credential1Title = "Degree";
                credential2Title = "School";
            } else if (section instanceof Experience) {
                credential1 = ((Experience) section).getCompany();
                credential2 = ((Experience) section).getRole();
                credential1Title = "Company";
                credential2Title = "Role";
            } else if (section instanceof Skill) {
                credential1 = ((Skill) section).getSkill();
                credential2 = ((Skill) section).getProficiency();
                credential1Title = "Skill";
                credential2Title = "Proficiency";
            } else {
                throw new IllegalArgumentException("Unknown ResumeSection type: " + section.getClass());
            }

            printBanner(section.getClass().getSimpleName());
            System.out.printf("[%s] %s\n", credential1Title, credential1);
            System.out.printf("[%s] %s\n", credential2Title, credential2);

            int counter = 0;
            System.out.println("Start Date: " + section.getStartDate());
            System.out.println("End Date: " + section.getEndDate());

            System.out.println("Achievements: " + section.getAchievements());

        }


    }

    public static void printBanner(String text) {
        int width = 30;
        String border = "=".repeat(width);

        int padding = (width - text.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;

        System.out.println(border);
        System.out.println(paddedText);
        System.out.println(border);
    }

}

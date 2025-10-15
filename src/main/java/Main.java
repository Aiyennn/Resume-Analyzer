import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Database database = new Database();

        JobKeywords jobKeywords = new JobKeywords();
        EducationAnalyzer educationAnalyzer = new EducationAnalyzer(jobKeywords);
        ExperienceAnalyzer experienceAnalyzer = new ExperienceAnalyzer(jobKeywords);
        SkillAnalyzer skillAnalyzer = new SkillAnalyzer(jobKeywords);

        MatchJobService matchJobService = new MatchJobService(database, educationAnalyzer, experienceAnalyzer, skillAnalyzer);
        AuthService authService = new AuthService(database);
        ResumeService resumeService = new ResumeService(database);

        AuthUI authUI = new AuthUI(authService, scanner);
        MatchJobUI matchJobUI = new MatchJobUI(matchJobService, scanner);
        ResumeUI resumeUI = new ResumeUI(resumeService, scanner);

        MainController mainController = new MainController(authUI, matchJobUI, resumeUI, scanner);

        mainController.app();

    }

}

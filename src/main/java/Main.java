import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
        Scanner scanner = new Scanner(System.in);

        AuthService authService = new AuthService(database);
        MatchJobService matchJobService = new MatchJobService(database);
        ResumeService resumeService = new ResumeService(database);

        AuthUI authUI = new AuthUI(authService, scanner);
        MatchJobUI matchJobUI = new MatchJobUI(matchJobService, scanner);
        ResumeUI resumeUI = new ResumeUI(resumeService, scanner);

        MainController mainController = new MainController(authUI, matchJobUI, resumeUI);
        mainController.app();

    }
}

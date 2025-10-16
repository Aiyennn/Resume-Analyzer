import java.util.Scanner;

public class MainController {
    private AuthUI authUI;
    private MatchJobUI matchJobUI;
    private ResumeUI resumeUI;
    private Scanner scanner;

    public MainController(AuthUI authUI, MatchJobUI matchJobUI, ResumeUI resumeUI, Scanner scanner){
        this.authUI = authUI;
        this.matchJobUI = matchJobUI;
        this.resumeUI = resumeUI;
        this.scanner = scanner;
    }

    public void app(){

        while (true) {

        User currentUser = authUI.displayMenu();

        if (currentUser == null) {
            System.out.println("Please Login: ");
        }

        if (currentUser instanceof JobSeeker) {
            mainMenu(currentUser);
        }

        if (currentUser instanceof  Employer) {
            employerMenu(currentUser);
        }


        }


    }

    // Job seeker menu || Rename
    public void mainMenu(User user) {

        while (true) {

        Util.printBanner("[Job seeker]: " + user.getName());
        System.out.println("1. Build Resume");
        System.out.println("2. Match Jobs");
        System.out.println("3. Logout");

        System.out.print("Select a Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                resumeUI.displayMenu((JobSeeker) user);
                break;
            case "2":
                matchJobUI.displayMenu((JobSeeker) user);
                break;
            case "3":
                return;
        }

    }
    }

    // Employer menu
    public void employerMenu(User user) {

        while (true) {

            Util.printBanner("[Employer]: " + user.getName());
            System.out.println("1. Post Job");
            System.out.println("2. View Posted Jobs");
            System.out.println("3. Logout");

            System.out.println("Select a Choice");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    matchJobUI.inputPostJob((Employer) user);
                    break;
                case "2":
                    matchJobUI.displayPostedJobs((Employer) user);
                    break;
                case "3":
                    return;
            }

        }

    }



}
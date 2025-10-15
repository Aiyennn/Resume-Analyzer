import java.sql.SQLOutput;
import java.util.Scanner;

public class AuthUI {

    private AuthService authService;
    private Scanner scanner;

    public AuthUI(AuthService authService, Scanner scanner) {
        this.authService = authService;
        this.scanner = scanner;
    }

    public User displayMenu() {

        while (true) {
        System.out.println("1. Job seeker Login");
        System.out.println("2. Job seeker Register");
        System.out.println("3. Employer Login");
        System.out.println("4. Employer Register");
        System.out.println("5. Exit");
        System.out.print("Choose an Option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                return jobseekerLoginInput();
            case "2":
                return jobseekerRegisterInput();
            case "3":
                return employerLoginInput();
            case "4":
                return employerRegisterInput();
            case "5":
                return null;
            default:
                System.out.println("Invalid input try again");
        }

        }

    }

    public JobSeeker jobseekerLoginInput(){

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        JobSeeker user = authService.jobSeekerlogin(email, password);

        return user;
    }

    public User jobseekerRegisterInput(){

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        JobSeeker user = authService.jobSeekerRegister(name, email, password);

        return user;
    }

    public Employer employerLoginInput() {
        return new Employer();
    }

    public Employer employerRegisterInput() {
        return new Employer();
    }

}
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
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose an Option: ");
        String choice = scanner.nextLine();

        User user = switch (choice) {
            case "1" -> loginInput();
            case "2" -> registerInput();
            case "0" -> {
                System.out.println("Bye");
                yield null;
            }
            default -> {
                System.out.println("Invalid choice, try again.");
                yield null;
            }
        };

        if (user != null) {
            return user;
        }

        }

    }


    public void displayLoginForm(){

        // Display

    }

    public void displayRegisterForm(){

        // Display

    }

    public User loginInput(){

        // Refer datatype on AuthService

        // Prompt the user for username and password

        // Call authService.login(username, password)

        // return the object from authService

        // if authService.login(username, password) returned null
            // Print that the login credential is invalid
            // Prompt the user if they want to register
            // call registerInput()

        // Delete ---------
        return new User();
        // Delete ---------
    }

    public User registerInput(){

        // Refer datatype on AuthService

        // Prompt for name, email, password, age,

        // Call authService.register(name, email, password, age)

        // Delete ---------
        return new User();
        // Delete ---------
    }
}
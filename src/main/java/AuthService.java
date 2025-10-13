public class AuthService {
    private Database database;

    public AuthService(Database database) {
        this.database = database;
    }

    public User login(String email, String password) {

        // Check if user exists

        // compare the encrypt password

        // return User

        // if user doesn't exists return null

        // Delete ---------
        return new User();
        // Delete ---------
    }

    public User register(String name, String email, String password, int age, String role) {

        // email must be unique

        // Save to db

        // return user

        // Delete ---------
        return new User();
        // Delete ---------
    }

}

import javax.xml.crypto.Data;

public class AuthService {

    private Database db;

    public AuthService (Database db) {
        this.db = db;
    }

    public User login() {
        return new User();
    };

    public User register() {
        return new User();
    }

}

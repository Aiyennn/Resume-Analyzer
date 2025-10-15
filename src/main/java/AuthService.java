public class AuthService {
    private Database database;

    public AuthService(Database database) {
        this.database = database;
    }

    public JobSeeker jobSeekerlogin(String email, String password) {

        try {
            JobSeeker user = database.getJobSeekerByEmail(email, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JobSeeker jobSeekerRegister(String name, String email, String password) {

        JobSeeker jobSeeker = new JobSeeker(name, email, password, null);

        try {

            if (database.userExists(email)) {
                System.out.println("User already exists please login");
                return null;
            }

            database.saveJobSeeker(jobSeeker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobSeeker;
    }

}

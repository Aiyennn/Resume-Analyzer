public class MainController {
    private AuthUI authUI;
    private MatchJobUI matchJobUI;
    private ResumeUI resumeUI;

    public MainController(AuthUI authUI, MatchJobUI matchJobUI, ResumeUI resumeUI){
        this.authUI = authUI;
        this.matchJobUI = matchJobUI;
        this.resumeUI = resumeUI;
    }

    public void app(){

        User currentUser = null;

        if (currentUser == null) {
            authUI.displayMenu();
        }


    }



}
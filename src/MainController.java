public class MainController {
    private AuthUI authUI;
    private ResumeBuilderUI resumeBuilderUI;
    private MatchJobUI matchJobUI;

    public MainController(AuthUI authUI, ResumeBuilderUI resumeBuilderUI, MatchJobUI matchJobUI){
        this.authUI = authUI;
        this.resumeBuilderUI = resumeBuilderUI;
        this.matchJobUI = matchJobUI;
    }

    public void app(){ // pass the user in method
        User user = authUI.loginInput();
    }

}
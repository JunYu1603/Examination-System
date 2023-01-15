
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author junyu
 */
public class Main extends Application {

    private Login winLogin;

    public void start(Stage mainStage) {
        winLogin = new Login();

    }

    public static void main(String args[]) {
        Application.launch(args);
    }
}


import java.io.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class Intropage extends Stage {
    private Login winLogin;
    

    public Intropage() {
        Label introTitle = new Label("Welcome To New Camasia\n" + "     Citizenship MCQ Test");
        introTitle.setStyle("-fx-font: normal bold 60px 'times new roman' "); 
       
        
        Button btnstart = new Button("Start");
        btnstart.setStyle("-fx-background-color: aqua;\n" +
                       "-fx-background-radius: 30;\n" +
                       "-fx-background-insets: 0;\n" +
                       "-fx-text-fill: black;" +
                       "-fx-padding: 10 50 10 50;");
        btnstart.setOnMouseEntered(e -> btnstart.setStyle("-fx-background-color: lightblue;\n" +
                       "-fx-background-radius: 30;\n" +
                       "-fx-background-insets: 0;\n" +
                       "-fx-text-fill: white;" +
                       "-fx-padding: 10 50 10 50;"));
        btnstart.setOnMouseExited(e -> btnstart.setStyle("-fx-background-color: aqua;\n" +
                       "-fx-background-radius: 30;\n" +
                       "-fx-background-insets: 0;\n" +
                       "-fx-text-fill: black;" +
                       "-fx-padding: 10 50 10 50;"));
        btnstart.setOnAction(e -> {
            this.close();
            Login winLogin = new Login();
        });
        
        
        // scene
        VBox intro = new VBox(introTitle, btnstart);
        intro.setSpacing(200);
        intro.setAlignment(Pos.CENTER);
        intro.setStyle("-fx-padding: 15;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 4;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;" +
                      "-fx-background-color: white;");
        this.setScene(new Scene(intro, 1000, 600, Color.WHITE));
        this.show();
       
    }

}

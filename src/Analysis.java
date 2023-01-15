import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Analysis extends Stage {

    // UI attributes and file
    private Label analysisTitle;
    private Label hs, ls, mean, median, mode, sd;
    private Label username, nation;
    private File trf = new File("./data", "test_result.txt");

    private Button btnViewResults, btnExit;
    private ArrayList<Integer> intList = new ArrayList<Integer>();
    private ArrayList<Double> douList = new ArrayList<Double>();

     public Analysis() {

        this.setTitle("Analysis of Examinee's Answers");
        // UI settings----------------------------------------------------------------------------------
        analysisTitle = new Label("Results Statistics");
        analysisTitle.setStyle("-fx-font: normal bold 60px 'arial';\n" + "-fx-text-fill: white;");
        analysisTitle.setLayoutX(250);
        analysisTitle.setLayoutY(100);

        hs= new Label("Highest Score    : " + getHighestScore() + "/25");
        hs.setStyle("-fx-font: normal 20px 'times new roman' ");
        hs.setLayoutX(320);
        hs.setLayoutY(220);

        ls = new Label("Lowest Score    : " + getLowestScore() + "/25");
        ls.setStyle("-fx-font: normal 20px 'times new roman' ");
        ls.setLayoutX(320);
        ls.setLayoutY(260);

        mean = new Label("Mean   : " + getMean() + "/25");
        mean.setStyle("-fx-font: normal 20px 'times new roman' ");
        mean.setLayoutX(320);
        mean.setLayoutY(300);

        median = new Label("Median   : " + getMedian());
        median.setStyle("-fx-font: normal 20px 'times new roman' ");
        median.setLayoutX(320);
        median.setLayoutY(340);


        mode = new Label("Mode  : " + + getMode() +"/25");
        mode.setStyle("-fx-font: normal 20px 'times new roman' ");
        mode.setLayoutX(320);
        mode.setLayoutY(380);

        sd = new Label ("Standard Deviation : " + getStandardDeviation());
        sd.setStyle("-fx-font: normal 20px 'times new roman' ");
        sd.setLayoutX(320);
        sd.setLayoutY(420);


        username = new Label(Candidate.getName());
        username.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        username.setLayoutX(100);
        username.setLayoutY(500);

        nation = new Label(Candidate.getNationality());
        nation.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        nation.setLayoutX(220);
        nation.setLayoutY(500);

        //view results
        btnViewResults = new Button("View Results");
        btnViewResults.setLayoutX(600);
        btnViewResults.setLayoutY(500);
        btnViewResults.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnViewResults.setOnMouseEntered(e -> btnViewResults.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnViewResults.setOnMouseExited(e -> btnViewResults.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnViewResults.setOnAction(e -> {
            this.hide();
            Result winResult = new Result();
        });

        //exit button
        btnExit = new Button();
        btnExit.setText("Exit");
        btnExit.setLayoutX(800);
        btnExit.setLayoutY(500);
        btnExit.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnExit.setOnMouseEntered(e -> btnExit.setStyle("-fx-background-color: red;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnExit.setOnMouseExited(e -> btnExit.setStyle("-fx-background-color: white;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnExit.setOnAction(e -> {
            this.close();
        });
        // UI settings end------------------------------------------------------------------------------

        //create Analysis Pane
        Pane analysispane = new Pane();
        analysispane.setStyle("-fx-padding: 15;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 10;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: royalblue;"
                + "-fx-background-color: skyblue;");

        analysispane.getChildren().add(username);
        analysispane.getChildren().add(nation);
        analysispane.getChildren().add((analysisTitle));
        analysispane.getChildren().add(hs);
        analysispane.getChildren().add(ls);
        analysispane.getChildren().add(mean);
        analysispane.getChildren().add(median);
        analysispane.getChildren().add(mode);
        analysispane.getChildren().add(sd);
        analysispane.getChildren().add(btnViewResults);
        analysispane.getChildren().add(btnExit);


        Scene analysis = new Scene(analysispane, 1000, 600, Color.WHITE);
        this.setScene(analysis);
        this.show();

    }

    //calculations
    public int getHighestScore() {
        try {
            Scanner s = new Scanner(trf);
            int score;

            while(s.hasNextLine()) {
                String aLine = s.nextLine();
                Scanner iline = new Scanner(aLine);
                iline.useDelimiter(",");
                String s1 = null;
                for (int i = 0; i < 28; i++) {
                    s1 = iline.next();
                }
                score = Integer.parseInt(s1);
                intList.add(score);

                iline.close();
            }
            s.close();
            //print in console to check intList values
            System.out.println("List of Candidate Marks: " + intList);

            return MathCal.gettopMarks(intList);

        } catch(FileNotFoundException e) {
            System.out.println("File to read " + trf + " not found!");
            return 0;
        }

    }
    
    public int getLowestScore() {

         return MathCal.getleastMarks(intList);
    }

    public int getMean() {

         return MathCal.getMean(intList);
    }

    public double getMedian() {
        for (int i = 0; i < intList.size(); i++) {
            douList.add((double)intList.get(i));
        }
         return MathCal.getMedian(douList);
    }

    public int getMode() {

         return MathCal.getMode(intList);
    }

    public String getStandardDeviation() {

         return MathCal.getStandardDeviation(douList);
    }

}

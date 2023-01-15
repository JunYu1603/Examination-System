
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

/**
 *
 * @author amirul
 */
public class Result extends Stage {

    private Label name, country, score, grade, title, answers, correct, tell, question, canname;
    private ChoiceBox<String> username;
    private ArrayList<String> namelist = new ArrayList<>();
    private ArrayList<String> canAns = new ArrayList<>();
    private ArrayList<String> sysAns = new ArrayList<>();
    private File tr = new File("./data", "test_result.txt");
    private File ca = new File("./data", "question_list.txt");
    private Button btnresult, btnviewanal, btnexit;
    private String canName, canCountry, canGrade;
    private int canScore;
    private ImageView imgTable;

    public Result() {


        title = new Label("Results");
        title.setStyle("-fx-font: normal bold 20px 'aerial' ");
        title.setLayoutX(390);
        title.setLayoutY(30);
        
        canname = new Label("Candidate Name: ");
        canname.setStyle("-fx-font: normal bold 20px 'times new roman' ");
        canname.setLayoutX(30);
        canname.setLayoutY(30);

        name = new Label("Name: ");
        name.setStyle("-fx-font: normal bold 20px 'times new roman' ");
        name.setLayoutX(150);
        name.setLayoutY(200);

        country = new Label("Country: ");
        country.setStyle("-fx-font: normal bold 20px 'times new roman' ");
        country.setLayoutX(150);
        country.setLayoutY(250);

        score = new Label("Score: ");
        score.setStyle("-fx-font: normal bold 20px 'times new roman' ");
        score.setLayoutX(150);
        score.setLayoutY(300);

        grade = new Label("");
        grade.setStyle("-fx-font: normal bold 30px 'times new roman' ");
        grade.setLayoutX(150);
        grade.setLayoutY(350);

        question = new Label("");
        question.setStyle("-fx-font: normal bold 15px 'times new roman' ");
        question.setLayoutX(525);
        question.setLayoutY(50);

        answers = new Label("");
        answers.setStyle("-fx-font: normal bold 15px 'times new roman' ");
        answers.setLayoutX(600);
        answers.setLayoutY(50);

        correct = new Label("");
        correctAnswers();
        correct.setStyle("-fx-font: normal bold 15px 'times new roman' ");
        correct.setLayoutX(700);
        correct.setLayoutY(50);

        tell = new Label("");
        tell.setStyle("-fx-font: normal bold 15px 'times new roman' ");
        tell.setLayoutX(825);
        tell.setLayoutY(50);

        username = new ChoiceBox<String>();
        namelist();
        username.getItems().addAll(namelist);
        username.setStyle(" -fx-pref-width: 200;");
        username.setLayoutX(30);
        username.setLayoutY(60);
        
        Image imagetable = new Image(getClass().getResourceAsStream("./images/table.png"));
        imgTable = new ImageView(imagetable);
        imgTable.setLayoutX(470);
        imgTable.setLayoutY(38);
        imgTable.setFitHeight(510);
        imgTable.setFitWidth(450);
        imgTable.setOpacity(0.1);


        btnviewanal = new Button();
        btnviewanal.setText("View Analysis");
        btnviewanal.setLayoutX(500);
        btnviewanal.setLayoutY(600);
        btnviewanal.setStyle("-fx-background-color: lightgreen;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnviewanal.setOnMouseEntered(e -> btnviewanal.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnviewanal.setOnMouseExited(e -> btnviewanal.setStyle("-fx-background-color: lightgreen;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnviewanal.setOnAction(e -> {
            Analysis winAnalysis = new Analysis();
            this.hide();
        });

        btnexit = new Button();
        btnexit.setText("Exit");
        btnexit.setLayoutX(700);
        btnexit.setLayoutY(600);
        btnexit.setStyle("-fx-background-color: red;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnexit.setOnMouseEntered(e -> btnexit.setStyle("-fx-background-color: darkred;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnexit.setOnMouseExited(e -> btnexit.setStyle("-fx-background-color: red;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnexit.setOnAction((ActionEvent e) -> {
            this.close();
        });

        btnresult = new Button();
        btnresult.setText("Show Results");
        btnresult.setLayoutX(150);
        btnresult.setLayoutY(600);
        btnresult.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnresult.setOnMouseEntered(e -> btnresult.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnresult.setOnMouseExited(e -> btnresult.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnresult.setOnAction((ActionEvent e) -> {
            getCandidateResults();

            name.setText("Name: " + canName);
            country.setText("Country: " + canCountry);
            canScore = canScore * 100 / 25;
            score.setText("Score: " + canScore + "%");
            grade.setText(canGrade.toUpperCase());
            StringBuilder buildans = new StringBuilder();
            StringBuilder buildsystem = new StringBuilder();
            StringBuilder buildcheck = new StringBuilder();
            StringBuilder buildques = new StringBuilder();
            String system = "";
            String ans = "";
            String check = "";
            String ques = "";
            for (int i = 0; i < 25; i++) {
                buildans.append("\t" + canAns.get(i) + "\n");
                buildsystem.append("\t    " + sysAns.get(i) + "\n");
                buildques.append("\t" + (i + 1) + "\n");
                ans = canAns.get(i);
                system = sysAns.get(i);
                if (ans.equals(system)) {
                    buildcheck.append("Correct\n");
                } else {
                    buildcheck.append("Wrong\n");
                }
            }
            ans = buildans.toString();
            system = buildsystem.toString();
            check = buildcheck.toString();
            ques = buildques.toString();
            answers.setText("Your Answer\n" + ans);
            correct.setText("Correct Answer\n" + system);
            tell.setText("Result\n" + check);
            question.setText("Question#\n" + ques);
            System.out.println(system);

        });
        
        Pane resultpane = new Pane();
        resultpane.setStyle("-fx-padding: 15;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 10;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: royalblue;"
                + "-fx-background-color: skyblue;");
        resultpane.getChildren().add(title);
        resultpane.getChildren().add(canname);
        resultpane.getChildren().add(name);
        resultpane.getChildren().add(country);
        resultpane.getChildren().add(score);
        resultpane.getChildren().add(grade);
        resultpane.getChildren().add(question);
        resultpane.getChildren().add(answers);
        resultpane.getChildren().add(correct);
        resultpane.getChildren().add(tell);
        resultpane.getChildren().add(username);
        resultpane.getChildren().add(btnviewanal);
        resultpane.getChildren().add(btnexit);
        resultpane.getChildren().add(btnresult);
        resultpane.getChildren().add(imgTable);

        Scene result = new Scene(resultpane, 1000, 700);

        this.setScene(result);
        this.show();
    }

    public void namelist() {
        try {
            Scanner scan = new Scanner(tr);
            String name;

            while (scan.hasNextLine()) {
                String aLine = scan.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(",");
                while (sline.hasNext()) {
                    name = sline.next();
                    namelist.add(name);

                    sline.nextLine();
                }
                sline.close();
            }
            scan.close();
            // use for checking the arrray list in console
            System.out.println(namelist);

        } catch (FileNotFoundException e) {
            System.out.println("File to read " + tr + " not found!");
        }
    }

    public void getCandidateResults() {
        Scanner scan;
        String name;
        canName = "";
        canCountry = "";
        String ans;
        canScore = 0;
        canGrade = "";
        try {
            scan = new Scanner(tr);

            // check if user input is empty
            if (username.getValue() == null) {
                System.out.println("Please choose a candidate");
            } else {
                // scan file and verify candidate
                while (scan.hasNextLine()) {
                    String aLine = scan.nextLine();
                    Scanner sline = new Scanner(aLine);
                    sline.useDelimiter(",");
                    while (sline.hasNext()) {
                        name = sline.next();
                        if (name.equals(username.getValue())) {
                            canName = name;
                            canCountry = sline.next();
                            canAns.clear();
                            for (int k = 0; k < 25; k++) {
                                ans = sline.next();
                                canAns.add(ans);
                            }
                            canScore = Integer.parseInt(sline.next());
                            canGrade = sline.next();
                            System.out.println(canName);
                            System.out.println(canCountry);
                            System.out.println(canAns);
                            System.out.println(canScore);
                            System.out.println(canGrade);
                        }
                    }
                    sline.close();
                }

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + tr + " not found!");
        }
    }

    public void correctAnswers() {
        Scanner scan;
        String answer;
        try {
            scan = new Scanner(ca);
            String aLine = scan.nextLine();
            Scanner sline = new Scanner(aLine);
            sline.next();
            while (scan.hasNextLine()) {
                aLine = scan.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");

                while (sline.hasNext()) {
                    sline.next();
                    answer = sline.next();
                    sysAns.add(answer);

                    sline.nextLine();
                }
                sline.close();
            }

            scan.close();
            System.out.println(sysAns);
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + ca + " not found!");
        }
    }

}

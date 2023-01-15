
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author faraz
 */
public class Examination extends Stage {

    private File ql = new File("./data", "question_list.txt");
    private File tr = new File("./data", "test_result.txt");
    private int totQues = 0;
    private int activeQ = 1;
    private Label labQues, labQuesNo, labName, labNat, labGender, time, labEnd, labPage;
    private ImageView imgQues, imgchoice1, imgchoice2, imgchoice3, imgchoice4, imgTimer;
    private Label labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private ToggleGroup grpChoices;
    private Button btnPrev, btnNext, btnSubmit, btnResult, btnAnalysis;
    private LinkedList<String> ans = new LinkedList<String>();
    private ArrayList<String> correctans = new ArrayList<String>();
    private Pane mainPane;
    private Pane paneC;
    private Scene mainScene;
    private Login winLogin;
    private static long min, sec, hrs, totalSec = 0;
    private MediaPlayer mediaPlayer;

    private LinkedList<Question> quesList = new LinkedList<Question>();

    public Examination() {
        this.setTitle("Group 3 Examination Form");

        labName = new Label("");
        labName.setText("NAME : " + Candidate.getName());
        labName.setLayoutX(25);
        labName.setLayoutY(850);
        labName.setStyle("-fx-text-fill: white;"
                + "-fx-font: normal 20px 'aerial';");

        labNat = new Label("");
        labNat.setText("NATIONALITY : " + Candidate.getNationality());
        labNat.setLayoutX(220);
        labNat.setLayoutY(850);
        labNat.setStyle("-fx-text-fill: white;"
                + "-fx-font: normal 20px 'aerial';");

        labGender = new Label("");
        labGender.setText("GENDER : " + Candidate.getGender());
        labGender.setLayoutX(505);
        labGender.setLayoutY(850);
        labGender.setStyle("-fx-text-fill: white;"
                + "-fx-font: normal 20px 'aerial';");

        labPage = new Label("");
        labPage.setLayoutX(470);
        labPage.setLayoutY(760);

        GridPane canInfo = new GridPane();
        canInfo.add(labName, 0, 0);
        canInfo.add(labNat, 1, 0);
        canInfo.add(labGender, 2, 0);
        canInfo.setStyle("-fx-background-color: skyblue;"
                + "-fx-background-radius: 50px;");
        canInfo.setLayoutX(20);
        canInfo.setLayoutY(810);
        canInfo.setPadding(new Insets(15, 15, 15, 15));
        canInfo.setHgap(15);

        labEnd = new Label("Thank you for your participate");
        labEnd.setStyle("-fx-font-family:aerial;-fx-text-fill:white;-fx-font-size: 30pt;");
        labEnd.setLayoutX(260);
        labEnd.setLayoutY(200);

        labQuesNo = new Label("");
        labQuesNo.setLayoutX(25);
        labQuesNo.setLayoutY(50);
        labQuesNo.setStyle("-fx-font-family:serif;-fx-text-fill:royalblue;-fx-font-size: 20pt;");

        labQues = new Label("");
        labQues.setLayoutX(25);
        labQues.setLayoutY(100);
        labQues.setStyle("-fx-font-size: 15pt;-fx-font-weight:bold;");

        imgQues = new ImageView();
        imgQues.setLayoutX(25);
        imgQues.setLayoutY(75);
        imgQues.setFitHeight(150);
        imgQues.setFitWidth(150);

        labA = new Label("A");
        radChoice1 = new RadioButton("");

        labB = new Label("B");
        radChoice2 = new RadioButton("");

        labC = new Label("C");
        radChoice3 = new RadioButton("");

        labD = new Label("D");
        radChoice4 = new RadioButton("");

        time = new Label();
        time.setLayoutX(800);
        time.setLayoutY(70);
        time.setStyle("-fx-font-size: 25pt;-fx-font-weight:bold;");

        imgchoice1 = new ImageView();
        imgchoice1.setLayoutX(80);
        imgchoice1.setLayoutY(55);
        imgchoice1.setFitHeight(170);
        imgchoice1.setFitWidth(170);

        imgchoice2 = new ImageView();
        imgchoice2.setLayoutX(380);
        imgchoice2.setLayoutY(55);
        imgchoice2.setFitHeight(170);
        imgchoice2.setFitWidth(170);

        imgchoice3 = new ImageView();
        imgchoice3.setLayoutX(80);
        imgchoice3.setLayoutY(285);
        imgchoice3.setFitHeight(170);
        imgchoice3.setFitWidth(170);

        imgchoice4 = new ImageView();
        imgchoice4.setLayoutX(380);
        imgchoice4.setLayoutY(285);
        imgchoice4.setFitHeight(170);
        imgchoice4.setFitWidth(170);

        grpChoices = new ToggleGroup();
        radChoice1.setToggleGroup(grpChoices);
        radChoice2.setToggleGroup(grpChoices);
        radChoice3.setToggleGroup(grpChoices);
        radChoice4.setToggleGroup(grpChoices);

        paneC = new Pane();
        paneC.setLayoutX(25);
        paneC.setLayoutY(75);
        paneC.getChildren().add(imgQues);
        paneC.getChildren().add(imgchoice1);
        paneC.getChildren().add(imgchoice2);
        paneC.getChildren().add(imgchoice3);
        paneC.getChildren().add(imgchoice4);
        paneC.getChildren().add(labA);

        paneC.getChildren().add(radChoice1);
        paneC.getChildren().add(labB);

        paneC.getChildren().add(radChoice2);
        paneC.getChildren().add(labC);

        paneC.getChildren().add(radChoice3);
        paneC.getChildren().add(labD);

        paneC.getChildren().add(radChoice4);

        btnPrev = new Button("Prev");
        btnPrev.setLayoutX(300);
        btnPrev.setLayoutY(750);
        btnPrev.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnPrev.setOnMouseEntered(e -> btnPrev.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnPrev.setOnMouseExited(e -> btnPrev.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnPrev.setDisable(true);

        btnNext = new Button("Next");
        btnNext.setLayoutX(550);
        btnNext.setLayoutY(750);
        btnNext.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnNext.setOnMouseEntered(e -> btnNext.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnNext.setOnMouseExited(e -> btnNext.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));

        btnResult = new Button("View Result");
        btnResult.setLayoutX(190);
        btnResult.setLayoutY(380);
        btnResult.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnResult.setOnMouseEntered(e -> btnResult.setStyle("-fx-background-color: lightgreen;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnResult.setOnMouseExited(e -> btnResult.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));

        btnAnalysis = new Button("View Analysis");
        btnAnalysis.setLayoutX(600);
        btnAnalysis.setLayoutY(380);
        btnAnalysis.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnAnalysis.setOnMouseEntered(e -> btnAnalysis.setStyle("-fx-background-color: lightgreen;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnAnalysis.setOnMouseExited(e -> btnAnalysis.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));

        btnSubmit = new Button("Submit");
        btnSubmit.setLayoutX(800);
        btnSubmit.setLayoutY(830);
        btnSubmit.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnSubmit.setOnMouseEntered(e -> btnSubmit.setStyle("-fx-background-color: lightgreen;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnSubmit.setOnMouseExited(e -> btnSubmit.setStyle("-fx-background-color: green;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));

        totalSec = 300;
        Timer timer = new Timer();
        TimerTask timertask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        cvttime();
                        if (totalSec == 180) {
                            Media mediaFile = new Media(new File("./data/alert.wav").toURI().toString());
                            mediaPlayer = new MediaPlayer(mediaFile);
                            mediaPlayer.play();  // Play a notification Sound
                            time.setTextFill(Color.RED);
                        }

                        if (totalSec <= 0) {
                            timer.cancel();
                            time.setText("00:00:00");
                            Alert timeup = new Alert(Alert.AlertType.INFORMATION);
                            timeup.setContentText("Time's Up. The exam is ended.");
                            timeup.show();
                            saveAns();
                        }
                    }
                });
            }
        };
        timer.schedule(timertask, 0, 1000);

        Image imageTimer = new Image(getClass().getResourceAsStream("./images/timer.png"));
        imgTimer = new ImageView(imageTimer);
        imgTimer.setLayoutX(690);
        imgTimer.setLayoutY(50);
        imgTimer.setFitHeight(100);
        imgTimer.setFitWidth(100);

        readFromFile();
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, true);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, false);

        });
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, true);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, false);

        });
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, true);
            quesList.get(activeQ - 1).setSelected(3, false);

        });
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, true);

        });

        if (totQues == 1) {
            btnNext.setDisable(true);
        }
        btnNext.setOnAction(e -> {
            activeQ++;
            btnPrev.setDisable(false);
            if (activeQ == totQues) {
                btnNext.setDisable(true);
            }
            reloadQues();
        });
        btnPrev.setOnAction(e -> {
            activeQ--;
            btnNext.setDisable(false);
            if (activeQ == 1) {
                btnPrev.setDisable(true);
            }
            reloadQues();
        });
        btnResult.setOnAction(e -> {
            this.hide();
            Result winResult = new Result();
        });

        btnAnalysis.setOnAction(e -> {
            this.hide();
            Analysis winAnalysis = new Analysis();
        });

        btnSubmit.setOnAction(e -> {
            timer.cancel();
            saveAns();
            this.hide();
            Pane paneEnd = new Pane();
            paneEnd.setStyle("-fx-padding: 15;"
                    + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 10;"
                    + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;"
                    + "-fx-border-color: royalblue;"
                    + "-fx-background-color: skyblue;");
            canInfo.setLayoutX(40);
            canInfo.setLayoutY(510);
            paneEnd.getChildren().add(labEnd);
            paneEnd.getChildren().add(btnResult);
            paneEnd.getChildren().add(btnAnalysis);
            paneEnd.getChildren().add(canInfo);

            Scene endExam = new Scene(paneEnd, 1000, 600);
            this.setTitle("Group 3");
            this.setScene(endExam);
            this.show();
        });

        mainPane = new Pane();

        mainPane.setStyle(
                "-fx-padding: 15;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 10;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: royalblue;"
                + "-fx-background-color: white;");
        mainPane.getChildren().add(canInfo);
        mainPane.getChildren().add(time);
        mainPane.getChildren().add(imgTimer);
        mainPane.getChildren().add(labQuesNo);
        mainPane.getChildren().add(labQues);
        mainPane.getChildren().add(paneC);
        mainPane.getChildren().add(btnNext);
        mainPane.getChildren().add(labPage);
        mainPane.getChildren().add(btnPrev);
        mainPane.getChildren().add(btnSubmit);

        mainScene = new Scene(mainPane, 1000, 900);

        this.setScene(mainScene);

        reloadQues();

        this.show();
    }

    public void reloadQues() {
        labQuesNo.setText("Question " + Integer.toString(activeQ));
        labQues.setText(quesList.get(activeQ - 1).getTheQues());
        radChoice1.setText(quesList.get(activeQ - 1).getChoice(0));
        radChoice2.setText(quesList.get(activeQ - 1).getChoice(1));
        radChoice3.setText(quesList.get(activeQ - 1).getChoice(2));
        radChoice4.setText(quesList.get(activeQ - 1).getChoice(3));
        imgQues.setImage(null);
        imgchoice1.setImage(null);
        imgchoice2.setImage(null);
        imgchoice3.setImage(null);
        imgchoice4.setImage(null);
        labPage.setText(Integer.toString(activeQ) + " / 25");
        if (quesList.get(activeQ - 1).getType() == 1) {
            labA.setLayoutX(30);
            labA.setLayoutY(75);
            radChoice1.setLayoutX(50);
            radChoice1.setLayoutY(75);
            labB.setLayoutX(30);
            labB.setLayoutY(125);
            radChoice2.setLayoutX(50);
            radChoice2.setLayoutY(125);
            labC.setLayoutX(30);
            labC.setLayoutY(175);
            radChoice3.setLayoutX(50);
            radChoice3.setLayoutY(175);
            labD.setLayoutX(30);
            labD.setLayoutY(225);
            radChoice4.setLayoutX(50);
            radChoice4.setLayoutY(225);
        }
        if (quesList.get(activeQ - 1).getType() == 2) {

            String image = quesList.get(activeQ - 1).getQuesPic();
            Image img = new Image(getClass().getResourceAsStream("./images/" + image));
            imgQues.setImage(img);
            labA.setLayoutX(30);
            labA.setLayoutY(275);
            radChoice1.setLayoutX(50);
            radChoice1.setLayoutY(275);
            labB.setLayoutX(30);
            labB.setLayoutY(325);
            radChoice2.setLayoutX(50);
            radChoice2.setLayoutY(325);
            labC.setLayoutX(30);
            labC.setLayoutY(375);
            radChoice3.setLayoutX(50);
            radChoice3.setLayoutY(375);
            labD.setLayoutX(30);
            labD.setLayoutY(425);
            radChoice4.setLayoutX(50);
            radChoice4.setLayoutY(425);
        }
        if (quesList.get(activeQ - 1).getType() == 3) {
            radChoice1.setText("");
            radChoice2.setText("");
            radChoice3.setText("");
            radChoice4.setText("");
            String image1 = quesList.get(activeQ - 1).getChoice(0);
            Image img1 = new Image(getClass().getResourceAsStream("./images/" + image1));
            imgchoice1.setImage(img1);
            radChoice1.setLayoutX(50);
            radChoice1.setLayoutY(125);
            labA.setLayoutX(30);
            labA.setLayoutY(125);

            String image2 = quesList.get(activeQ - 1).getChoice(1);
            Image img2 = new Image(getClass().getResourceAsStream("./images/" + image2));
            imgchoice2.setImage(img2);
            radChoice2.setLayoutX(350);
            radChoice2.setLayoutY(125);
            labB.setLayoutX(330);
            labB.setLayoutY(125);

            String image3 = quesList.get(activeQ - 1).getChoice(2);
            Image img3 = new Image(getClass().getResourceAsStream("./images/" + image3));
            imgchoice3.setImage(img3);
            radChoice3.setLayoutX(50);
            radChoice3.setLayoutY(355);
            labC.setLayoutX(30);
            labC.setLayoutY(355);

            String image4 = quesList.get(activeQ - 1).getChoice(3);
            Image img4 = new Image(getClass().getResourceAsStream("./images/" + image4));
            imgchoice4.setImage(img4);
            radChoice4.setLayoutX(350);
            radChoice4.setLayoutY(355);
            labD.setLayoutX(330);
            labD.setLayoutY(355);

        }
        radChoice1.setSelected(quesList.get(activeQ - 1).getSelected(0));
        radChoice2.setSelected(quesList.get(activeQ - 1).getSelected(1));
        radChoice3.setSelected(quesList.get(activeQ - 1).getSelected(2));
        radChoice4.setSelected(quesList.get(activeQ - 1).getSelected(3));

    }

    public void readFromFile() {
        Scanner sfile;
        int type;
        char answer;
        String theQues;
        String choices[] = new String[4];
        String quesPic;
        Question ques;
        try {
            sfile = new Scanner(ql);
            String aLine = sfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totQues = Integer.parseInt(sline.next());
            for (int k = 1; k <= totQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                type = Integer.parseInt(sline.next());
                answer = sline.next().charAt(0);
                theQues = sline.next();
                quesPic = "";
                if (type == 2) {
                    quesPic = sline.next();
                }
                choices[0] = sline.next();
                choices[1] = sline.next();
                choices[2] = sline.next();
                choices[3] = sline.next();
                sline.close();
                ques = new Question(type, answer, theQues, choices, quesPic);
                quesList.add(ques);
                String cans = "" + answer;
                correctans.add(cans);

            }
            sfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + ql + " not found!");
        }

    }

    public void saveAns() {

        try {
            int totScore = 0;
            char selected;

            PrintWriter pw = new PrintWriter(new FileWriter(tr, true));
            pw.print(Candidate.getName() + ",");
            pw.print(Candidate.getNationality() + ",");
            for (int k = 0; k < totQues; k++) {

                // Get the chosen answer and store it in the CSV File.
                if (quesList.get(k).getSelected(0)) {
                    pw.print(labA.getText() + ",");
                    ans.add(labA.getText());
                } else if (quesList.get(k).getSelected(1)) {
                    pw.print(labB.getText() + ",");
                    ans.add(labB.getText());
                } else if (quesList.get(k).getSelected(2)) {
                    pw.print(labC.getText() + ",");
                    ans.add(labC.getText());
                } else if (quesList.get(k).getSelected(3)) {
                    pw.print(labD.getText() + ",");
                    ans.add(labD.getText());
                }

            }

            for (int i = 0; i <= correctans.size() - 1; i++) {
                if (ans.get(i).equals(correctans.get(i))) {
                    totScore += 1;
                }
            }
            pw.print(totScore + ",");
            System.out.println(totScore);
            if (totScore >= 18) {
                pw.print("pass");
            } else {
                pw.print("fail");
            }
            pw.println();
            pw.close();

        } catch (IOException e) {
        }
    }

    public String format(long value) {
        if (value < 10) {
            return 0 + "" + value;
        }
        return value + "";
    }

    public void cvttime() {
        min = TimeUnit.SECONDS.toMinutes(totalSec);
        sec = totalSec - (min * 60);
        hrs = TimeUnit.MINUTES.toHours(min);
        min = min - (hrs * 60);
        time.setText(format(hrs) + ":" + format(min) + ":" + format(sec));
        totalSec--;

    }

}

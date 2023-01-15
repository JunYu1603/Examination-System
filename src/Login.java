
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author junyu
 */
public class Login extends Stage {

    // UI Login attrributes and file
    private Label logintitle, logintitle2, loginreq;
    private ChoiceBox<String> username;
    private ArrayList<String> namelist = new ArrayList<>();
    private PasswordField password;
    private Button btnlogin, btnGotoSignup;
    private File cf = new File("./data", "candidate.txt");
    private File tr = new File("./data", "test_result.txt");
    private ImageView iconUser, iconPassword, iconIntro, iconLogin, iconUID;

    // User Input Details attributes
    private Label UIDtitle, UIDdes, by, gd, nt;
    private Button btnproceed;
    private TextField birthyear;
    private ChoiceBox<String> gender;
    private String log[] = {"Male", "Female"};
    private ChoiceBox<String> nationality;
    private String nation[] = {"New Zealand", "Canada", "Germany", "Federal Micronesia", "Jamaica"};

    // attributes of savetoraf method
    private static final int data_size = 46;
    private static final int name_size = 20;
    private static final int password_size = 6;
    private String name, pwd;

    public Login() {

        // UI intropage ----------------------------------------------------------------------------------
        Label introTitle = new Label("Welcome To New Camasia\n" + "     Citizenship MCQ Test");
        introTitle.setStyle("-fx-font: normal bold 50px 'aerial';"
                + "-fx-text-fill: white;");
        introTitle.setLayoutX(300);
        introTitle.setLayoutY(80);

        Label introdes = new Label("                This quiz consists of 25 questions,\n "
                + "you will have 10 minutes to answer the questions.\n"
                + "         You can start the quiz once you are ready, \n          hence click on \"Start\" button to proceed.");
        introdes.setLayoutX(400);
        introdes.setLayoutY(280);
        introdes.setStyle("-fx-font: normal bold 18px 'aerial';"
                + "-fx-text-fill: white;");
        
        Image imageIntro = new Image(getClass().getResourceAsStream("./images/earth.png"));
        iconIntro = new ImageView(imageIntro);
        iconIntro.setLayoutX(120);
        iconIntro.setLayoutY(180);
        iconIntro.setFitHeight(200);
        iconIntro.setFitWidth(170);

        Button btnstart = new Button("Start");
        btnstart.setLayoutX(450);
        btnstart.setLayoutY(500);
        btnstart.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 15 55 15 55;");
        btnstart.setOnMouseEntered(e -> btnstart.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 15 55 15 55;"));
        btnstart.setOnMouseExited(e -> btnstart.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 15 55 15 55;"));
        btnstart.setOnAction(e -> {
            this.close();
            // jump to login scene
            Pane loginpane = new Pane();
            loginpane.setStyle("-fx-padding: 15;"
                    + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 10;"
                    + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;"
                    + "-fx-border-color: royalblue;"
                    + "-fx-background-color: skyblue;");
            loginpane.getChildren().add(logintitle);
            loginpane.getChildren().add(logintitle2);
            loginpane.getChildren().add(loginreq);
            loginpane.getChildren().add(iconUser);
            loginpane.getChildren().add(iconPassword);
            loginpane.getChildren().add(iconLogin);
            loginpane.getChildren().add(username);
            loginpane.getChildren().add(password);
            loginpane.getChildren().add(btnlogin);
            //loginpane.getChildren().add(btnGotoSignup);
            Scene login = new Scene(loginpane, 1000, 600);
            this.setTitle("Group 3");
            this.setScene(login);
            this.show();
        });

        // scene
        Pane intro = new Pane(introTitle, introdes, btnstart, iconIntro);

        intro.setStyle("-fx-padding: 15;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 10;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: royalblue;"
                + "-fx-background-color: skyblue;");
        this.setScene(new Scene(intro, 1000, 600));
        this.setTitle("Group 3");
        this.show();
        // IntropageUI ------------------------------------------------------------------------------------

        // Login UI ----------------------------------------------------------------------------------------
        logintitle = new Label("Candidate Login");
        logintitle.setStyle("-fx-font: normal bold 60px 'aerial';"
                + "-fx-text-fill: white;");
        logintitle.setLayoutX(100);
        logintitle.setLayoutY(100);

        logintitle2 = new Label("Login to access examination form");
        logintitle2.setStyle("-fx-font: normal 30px 'aerial';"
                + "-fx-text-fill: white;");
        logintitle2.setLayoutX(110);
        logintitle2.setLayoutY(200);

        loginreq = new Label("Instructions: \n"
                + "  >>> Please select your full name according to your identify card. \n"
                + "  >>> Enter the password that is given to you to prior the test."
        );
        loginreq.setStyle("-fx-font: normal 20px 'aerial';"
                + "-fx-text-fill: white;");
        loginreq.setLayoutX(30);
        loginreq.setLayoutY(430);

        Image imageUser = new Image(getClass().getResourceAsStream("./images/user.png"));
        Image imagePassword = new Image(getClass().getResourceAsStream("./images/password.png"));

        iconUser = new ImageView(imageUser);
        iconUser.setLayoutX(600);
        iconUser.setLayoutY(100);
        iconUser.setFitHeight(50);
        iconUser.setFitWidth(50);
        iconPassword = new ImageView(imagePassword);
        iconPassword.setLayoutX(600);
        iconPassword.setLayoutY(165);
        iconPassword.setFitHeight(50);
        iconPassword.setFitWidth(50);
        
        Image imageLogin = new Image(getClass().getResourceAsStream("./images/can login.png"));
        iconLogin = new ImageView(imageLogin);
        iconLogin.setLayoutX(250);
        iconLogin.setLayoutY(250);
        iconLogin.setFitHeight(170);
        iconLogin.setFitWidth(170);

        username = new ChoiceBox<String>();
        //call method namelist to access arraylist
        namelist();
        username.getItems().addAll(namelist);
        username.setStyle(" -fx-pref-width: 200;");
        username.setLayoutX(680);
        username.setLayoutY(110);

        password = new PasswordField();
        password.setPromptText("Password");
        password.setLayoutX(680);
        password.setLayoutY(175);
        password.setAlignment(Pos.BASELINE_LEFT);

        btnlogin = new Button();
        btnlogin.setText("Login");
        btnlogin.setLayoutX(650);
        btnlogin.setLayoutY(400);
        btnlogin.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnlogin.setOnMouseEntered(e -> btnlogin.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnlogin.setOnMouseExited(e -> btnlogin.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnlogin.setOnAction(e -> {
            validation();
        });

        // call method to save data from txt file to dat file when accessing login page
        try {
            savetoraf();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        // login UI ---------------------------------------------------------------------------------

        // User Input UI ----------------------------------------------------------------------------
        UIDtitle = new Label("Personal Detail Form");
        UIDtitle.setStyle("-fx-font: normal bold 50px 'aerial';"
                + "-fx-text-fill: white;");
        UIDtitle.setLayoutX(280);
        UIDtitle.setLayoutY(70);

        UIDdes = new Label("                           Please fill in your personal details.\n"
                + "Once you are ready please click on \"Proceed\" button to start the quiz ");
        UIDdes.setStyle("-fx-font: normal 20px 'aerial';"
                + "-fx-text-fill: white;");
        UIDdes.setLayoutX(230);
        UIDdes.setLayoutY(130);
        
        Image imageUID = new Image(getClass().getResourceAsStream("./images/pdi.png"));
        iconUID = new ImageView(imageUID);
        iconUID.setLayoutX(120);
        iconUID.setLayoutY(200);
        iconUID.setFitHeight(200);
        iconUID.setFitWidth(200);

        by = new Label("Birthyear     : ");
        by.setStyle("-fx-font: normal 20px 'aerial';"
                + "-fx-text-fill: white;");
        by.setLayoutX(380);
        by.setLayoutY(230);

        gd = new Label("Gender        : ");
        gd.setStyle("-fx-font: normal 20px 'aerial';"
                + "-fx-text-fill: white;");
        gd.setLayoutX(380);
        gd.setLayoutY(300);

        nt = new Label("Nationality   : ");
        nt.setStyle("-fx-font: normal 20px 'aerial';"
                + "-fx-text-fill: white;");
        nt.setLayoutX(380);
        nt.setLayoutY(360);

        birthyear = new TextField();
        birthyear.setLayoutX(560);
        birthyear.setLayoutY(220);

        gender = new ChoiceBox<String>();
        gender.setStyle(" -fx-pref-width: 200;");
        gender.getItems().addAll(log);
        gender.setLayoutX(560);
        gender.setLayoutY(290);

        nationality = new ChoiceBox<String>();
        nationality.setStyle(" -fx-pref-width: 200;");
        nationality.getItems().addAll(nation);
        nationality.setLayoutX(560);
        nationality.setLayoutY(360);

        btnproceed = new Button();
        btnproceed.setText("Proceed");
        btnproceed.setLayoutX(420);
        btnproceed.setLayoutY(500);
        btnproceed.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;");
        btnproceed.setOnMouseEntered(e -> btnproceed.setStyle("-fx-background-color: lightblue;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: white;"
                + "-fx-padding: 10 50 10 50;"));
        btnproceed.setOnMouseExited(e -> btnproceed.setStyle("-fx-background-color: aqua;\n"
                + "-fx-background-radius: 30;\n"
                + "-fx-background-insets: 0;\n"
                + "-fx-text-fill: black;"
                + "-fx-padding: 10 50 10 50;"));
        btnproceed.setOnAction(e -> {
            savedata();
        });
    }
    // User Input Details UI ---------------------------------------------------------------------------

    // candidate authentication 
    public void validation() {
        Scanner scan;
        String name = null, pwd = null;
        Boolean valid = false;
        try {
            scan = new Scanner(cf);

            // check if user input is empty
            if (username.getValue() == null || password.getText().isEmpty()) {
                Alert errorlogin = new Alert(AlertType.ERROR);
                errorlogin.setContentText("Login Unsuccessfully.\n"
                        + "Empty Input.\n"
                        + "Please enter your name and password.\n");
                errorlogin.show();
            } else {
                // scan file and verify candidate
                while (scan.hasNextLine() && valid == false) {
                    String aLine = scan.nextLine();
                    Scanner sline = new Scanner(aLine);
                    sline.useDelimiter(",");
                    while (sline.hasNext()) {
                        name = sline.next();
                        pwd = sline.next();
                        if (name.equals(username.getValue()) && pwd.equals(password.getText())) {
                            this.hide();
                            //if login successfully, create user input scene
                            Pane UIDpane = new Pane();
                            UIDpane.setStyle("-fx-padding: 15;"
                                    + "-fx-border-style: solid inside;"
                                    + "-fx-border-width: 10;"
                                    + "-fx-border-insets: 5;"
                                    + "-fx-border-radius: 5;"
                                    + "-fx-border-color: royalblue;"
                                    + "-fx-background-color: skyblue;");
                            UIDpane.getChildren().add(UIDtitle);
                            UIDpane.getChildren().add(UIDdes);
                            UIDpane.getChildren().add(iconUID);
                            UIDpane.getChildren().add(by);
                            UIDpane.getChildren().add(gd);
                            UIDpane.getChildren().add(nt);
                            UIDpane.getChildren().add(birthyear);
                            UIDpane.getChildren().add(gender);
                            UIDpane.getChildren().add(nationality);
                            UIDpane.getChildren().add(btnproceed);
                            Scene UID = new Scene(UIDpane, 1000, 600);
                            this.setTitle("Group 3");
                            this.setScene(UID);
                            this.show();
                            valid = true;
                            Candidate.setName(name);
                            Candidate.setPassword(pwd);
                        }

                    }
                    sline.close();
                }
                // invalid candidate message
                if (valid == false) {
                    Alert errorlogin = new Alert(AlertType.ERROR);
                    errorlogin.setContentText("Login Unsuccessfully.\n"
                            + "Incorrect full name or password.\n"
                            + "Please READ through instructions \n"
                            + "& Try Again.");
                    errorlogin.show();
                }
                scan.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + cf + " not found!");
        }
    }

    // method to read candidate name and store in arraylist
    public void namelist() {
        try {
            Scanner scan = new Scanner(cf);
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
            System.out.println("File to read " + cf + " not found!");
        }
    }

    // save data from candidate.txt to serialized file
    public void savetoraf() throws FileNotFoundException, IOException {

        RandomAccessFile rafFile = new RandomAccessFile("candidate.dat", "rw");
        Scanner scan;

        try {
            scan = new Scanner(cf);

            while (scan.hasNextLine()) {
                String aLine = scan.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(",");
                while (sline.hasNext()) {
                    name = sline.next();
                    pwd = sline.next();
                    writeString(rafFile, name, name_size);
                    writeString(rafFile, pwd, password_size);
                }
                sline.close();

            }
            rafFile.close();
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + cf + " not found!");
        }
    }

    //convert characters to string
    public static void writeString(RandomAccessFile file, String text, int fixedSize) throws IOException {
        int size = text.length();
        if (size <= fixedSize) {
            file.writeChars(text);
            for (int i = size; i < fixedSize; i++) {
                file.writeChar(' ');
            }
        } else {
            file.writeChars(text.substring(0, fixedSize));
        }
    }

    // get user input details
    public void savedata() {
        int by = 0;
        boolean emptyinput = true;

        try {
            // ensure candidate enter birthyear with only 4 digits
            if (birthyear.getText().length() == 4) {
                by = Integer.parseInt(birthyear.getText());
                emptyinput = false;

            } else {
                Alert errorcount = new Alert(AlertType.ERROR);
                errorcount.setContentText("Birthyear input must have 4 digits only. \n"
                        + "Please enter correct value.");
                errorcount.show();
            }

            // if choicbox of gender & nationality is null
            if (gender.getValue() == null || nationality.getValue() == null || emptyinput == true) {
                Alert errorempty = new Alert(AlertType.ERROR);
                errorempty.setContentText("Birthyear, Gender or NationalityField is empty."
                        + "Please select the options.");
                errorempty.show();
            } else if (by < 1950 || by > 2010) {

                Alert errorvalue = new Alert(AlertType.ERROR);
                errorvalue.setContentText("Birthyear input must be within range 1950 to 2010. \n"
                        + "Please enter correct value.");
                errorvalue.show();

            } else {
                Candidate.setBirthyear(by);
                Candidate.setGender(gender.getValue());
                Candidate.setNationality(nationality.getValue());

                //check console output if the user information is accessable
                System.out.println("Name        :" + Candidate.getName() + "\n"
                        + "Password    :" + Candidate.getPassword() + "\n"
                        + "Gender      :" + Candidate.getGender() + "\n"
                        + "Birthyear   :" + Candidate.getBirthyear() + "\n"
                        + "Nationality :" + Candidate.getNationality());

                this.hide();
                // CREATE AND JUMP TO EXAMINATION FORM
                // Exam winexam = new Exam();
                
                Examination exam = new Examination();
            }

        } catch (NumberFormatException e) {

        }

    }

}

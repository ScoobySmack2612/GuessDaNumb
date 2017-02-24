/**
 * Created by Heron on 2/10/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StackPopLevel;
import java.util.Random;
import java.util.ArrayList;

public class Main extends Application {

    Stage window;
    Scene menu, game1, wonGame, lostGame;
    Button button, gameButton, restart, quit;
    int guesses = 0;
    int chosen;
    int userGuess;
    String endMsg;
    String againTxt = "Let's play Again!";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Welcome to Guess the Number");

        Label welcome = new Label("Welcome to Guess the Number!!");
        Label start = new Label("Click below to start");

        ArrayList<String> answers = new ArrayList<String>();

        button = new Button("Click Me");
        button.setOnAction(e -> {
            Random rand = new Random();
            chosen = rand.nextInt(51);
            window.setScene(game1);
        });

        quit = new Button("Abort!");
        quit.setOnAction(e -> {
            window.close();
        });

        VBox menu1 = new VBox(10);
        menu1.setPadding(new Insets(15));
        menu1.getChildren().addAll(welcome, start);
        menu1.setAlignment(Pos.CENTER);

        VBox menu2 = new VBox(20);
        menu2.getChildren().addAll(button,quit);
        menu2.setAlignment(Pos.CENTER);
        menu2.setPadding(new Insets(0,0,100,0));

        BorderPane grandeScheme = new BorderPane();
        grandeScheme.setCenter(menu1);
        grandeScheme.setBottom(menu2);

        menu = new Scene(grandeScheme, 450, 400);


        //scene 2 - Game1
        Label howToPlay = new Label("Pick a number between 0 and 50... You have 5 guesses, Choose wisely ;)");
        Label gameText = new Label("Enter a number!");
        TextField userIn = new TextField();

        Label result = new Label("");

        Label userAnswers = new Label("Your Guesses:");
        Label showAnswers = new Label("");

        gameButton = new Button("Submit");
        gameButton.setOnAction(e -> {
            if (guesses <=4) {
                try {
                    int userGuess = Integer.parseInt(userIn.getText());
                    guesses++;
                    String numberComp = compInt(userGuess, chosen);
                    userIn.clear();

                    if (numberComp.contains("Winner!")) {
                        guesses = 0;
                        answers.clear();
                        showAnswers.setText("");
                        result.setText("");
                        window.setScene(wonGame);
                    } else if (guesses == 5 && numberComp.contains("Winner!")){
                        guesses = 0;
                        answers.clear();
                        showAnswers.setText("");
                        result.setText("");
                        window.setScene(wonGame);
                    } else if(guesses==5 && !numberComp.contains("Winner!")) {
                        guesses = 0;
                        answers.clear();
                        showAnswers.setText("");
                        result.setText("");
                        window.setScene(lostGame);
                    }else {
                        String convertToString = Integer.toString(userGuess);
                        answers.add(convertToString);

                        String toDisplay = String.join(", ", answers);

                        showAnswers.setText(toDisplay);

                        String setResult = ("Oh I'm sorry" + numberComp);
                        result.setText(setResult);
                    }
                } catch (NumberFormatException exception) {
                    result.setText("Please enter integer!! I wont count that as a guess");
                    userIn.clear();
                }
            }
        });

        VBox guessesTxt = new VBox(20);
        guessesTxt.getChildren().addAll(userAnswers,showAnswers);

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(howToPlay,gameText, result, userIn, gameButton);
        layout2.setAlignment(Pos.CENTER);

        BorderPane grandeGameScheme = new BorderPane();
        grandeGameScheme.setCenter(layout2);
        grandeGameScheme.setBottom(guessesTxt);

        game1 = new Scene(grandeGameScheme, 450, 400);

        //Scene 3 - wonGame
        Label resultTxt = new Label("You Won Congratulations!!!");
        Label again = new Label(againTxt);

        //wonGame buttons(s)
        restart = new Button("I'm Cool and I want to try again");
        restart.setOnAction(e -> {
            window.setScene(menu);
        });
        quit = new Button("Nah, I want to leave");
        quit.setOnAction(e -> {
            window.close();
        });

        //wonGame layout
        //Text in layout will be vertical and centered messages
        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(resultTxt,again);
        layout3.setAlignment(Pos.CENTER);

        //Buttons in layout will be horizontal and centered
        HBox l3Btns = new HBox(20);
        l3Btns.getChildren().addAll(restart,quit);
        l3Btns.setAlignment(Pos.CENTER);

        //combine layouts
        BorderPane grandeScheme2 = new BorderPane();
        grandeScheme2.setTop(layout3);
        grandeScheme2.setCenter(l3Btns);

        wonGame = new Scene(grandeScheme2, 450,450);


        //Scene 4 - lostGame
        Label resultTxt2 = new Label("I'm Sorry, but you failed to guess the correct number..");
        Label DGU = new Label("But don't give up I believe in you!!");

        //lostGame buttons(s)
        restart = new Button("I'm Cool and I want to try again");
        restart.setOnAction(e -> {
            window.setScene(menu);
        });
        quit = new Button("Nah, I want to leave");
        quit.setOnAction(e -> {
            window.close();
        });

        //lostGame layout
        //Text in layout will be vertical and centered messages
        VBox layout4 = new VBox(20);
        layout4.getChildren().addAll(resultTxt2,DGU);
        layout4.setAlignment(Pos.CENTER);

        //Buttons in layout will be horizontal and centered
        HBox l4Btns = new HBox(20);
        l4Btns.getChildren().addAll(restart,quit);
        l4Btns.setAlignment(Pos.CENTER);


        //combine layouts
        BorderPane grandeScheme3 = new BorderPane();
        grandeScheme3.setTop(layout4);
        grandeScheme3.setCenter(l4Btns);

        lostGame = new Scene(grandeScheme3, 450,450);

        window.setScene(menu);
        window.show();

    }

    static String compInt(int userGuess, int chosen) {

        String numberComp;
        numberComp = "";

        if (userGuess == chosen) {
            numberComp = "Winner!";
        } else if (userGuess > chosen) {
            if (userGuess > chosen && userGuess > 50) {
                numberComp = "not even in range, come on... pick a number between 0 and 50";
            } else {
                numberComp = " too large";
            }
        } else if (userGuess < chosen) {
            if (userGuess < chosen && userGuess < 0) {
                numberComp = "not even in range, come on... pick a number between 0 and 50";
            } else {
                numberComp = " too low";
            }
        }

        return numberComp;
    }
}


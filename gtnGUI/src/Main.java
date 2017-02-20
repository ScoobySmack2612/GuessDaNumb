/**
 * Created by Heron on 2/10/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StackPopLevel;
import java.util.Random;

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


        button = new Button("Click Me");
        button.setOnAction(e -> {
            Random rand = new Random();
            chosen = rand.nextInt(51);
            System.out.println("The number is " + chosen + " Guesses equals zero");
            window.setScene(game1);
        });

        VBox menu1 = new VBox(150);
        menu1.getChildren().addAll(welcome, start);

        StackPane menu2 = new StackPane();
        menu2.getChildren().add(button);

        BorderPane grandeScheme = new BorderPane();
        grandeScheme.setTop(menu1);
        grandeScheme.setCenter(menu2);

        menu = new Scene(grandeScheme, 450, 400);
        menu1.setAlignment(Pos.CENTER);


        //scene 2 - Game1
        Label gameText = new Label("Enter a number!");
        TextField userIn = new TextField();
        gameButton = new Button("Submit");
        gameButton.setOnAction(e -> {
            if (guesses <=4) {
                try {
                    int userGuess = Integer.parseInt(userIn.getText());
                    guesses++;
                    String numberComp = compInt(userGuess, chosen);
                    userIn.clear();
                    System.out.println(numberComp);

                    if (numberComp.contains("Winner!")) {
                        guesses = 0;
                        window.setScene(wonGame);
                    } else if (guesses == 5 && numberComp.contains("Winner!")){
                        guesses = 0;
                        window.setScene(wonGame);
                    } else if(guesses==5 && !numberComp.contains("Winner!")) {
                        guesses = 0;
                        window.setScene(lostGame);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Please enter integer!! I wont count that as a guess");
                    userIn.clear();
                }
            }
        });

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(gameText, userIn, gameButton);
        layout2.setAlignment(Pos.CENTER);
        game1 = new Scene(layout2, 450, 400);

        //Scene 3 - wonGame
        Label resultTxt = new Label("You Won Congratulations!!!");
        Label again = new Label(againTxt);

        //wonGame buttons(s)
        restart = new Button("I'm Cool and I want to try again");
        restart.setOnAction(e -> {
            window.setScene(menu);
        });
        quit = new Button("Nah, I'm Lame and just want to go...");

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
        Label resultTxt2 = new Label("I'm Sorry but you failed to guess the Correct number..");
        Label DGU = new Label("But don't give upp I believe in you!!");

        //lostGame buttons(s)
        restart = new Button("I'm Cool and I want to try again");
        restart.setOnAction(e -> {
            window.setScene(menu);
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


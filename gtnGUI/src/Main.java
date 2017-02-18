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
    Scene menu, game1;
    Button button;
    Button gameButton;
    int lives = 4;
    int userGuess;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window =primaryStage;
        window.setTitle("Welcome to Guess the Number");

        Label welcome = new Label("Welcome to Guess the Number!!");
        Label start = new Label("Click below to start");


        button = new Button("Click Me");
        button.setOnAction(e -> {
            window.setScene(game1);
        });

        VBox menu1 = new VBox(150);
        menu1.getChildren().addAll(welcome,start);

        StackPane menu2 = new StackPane();
        menu2.getChildren().add(button);

        BorderPane grandeScheme = new BorderPane();
        grandeScheme.setTop(menu1);
        grandeScheme.setCenter(menu2);

        menu = new Scene(grandeScheme,450,400);
        menu1.setAlignment(Pos.CENTER);


        //scene 2 - Game1
        Label gameText = new Label("Enter a number!");
        TextField userIn = new TextField();
        gameButton = new Button("Submit");
        gameButton.setOnAction(e ->{
            Random rand = new Random();
            int chosen = rand.nextInt(51);
            userGuess = Integer.valueOf(userIn.getText());
            userIn.clear();
            String numberComp = compInt(userGuess, chosen);
            String checkWin = winCheck(numberComp, chosen, userGuess);
        });

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(gameText,userIn,gameButton);
        layout2.setAlignment(Pos.CENTER);
        game1 = new Scene(layout2,450,400);


        window.setScene(menu);
        window.show();

    }

    static String compInt(int userGuess, int chosen){

        String numberComp;
        numberComp = "";

        if(userGuess==chosen){
            numberComp="Winner!";
        }
        else if (userGuess > chosen) {
            if (userGuess > chosen && userGuess> 50) {
                numberComp = "not even in range, come on... pick a number between 0 and 50";
            } else {
                numberComp = " too large";
            }
        }
        else if (userGuess < chosen) {
            if (userGuess < chosen && userGuess < 0) {
                numberComp = "not even in range, come on... pick a number between 0 and 50";
            } else {
                numberComp = " too low";
            }
        }

        return numberComp;
    }
    public String winCheck(String numberComp, int chosen, int userGuess){
        String checkWin = "";
        for(lives = 4; lives>=0; lives-=1) {
            if(lives == 0 && numberComp.equals("Winner!")){
                checkWin = "Won on fifth try";
                return checkWin;

            }
            else if(lives ==0 && !numberComp.equals("Winner")){
                checkWin = "loser";
                return checkWin;
            }
            else {
                if (numberComp.equals("Winner!")) {;
                    checkWin = "Winner";
                } else {

                    checkWin = "Sorry, but that answer was" + numberComp + " you have " + lives + " lives left.";
                    numberComp = compInt(userGuess, chosen);
                }
            }
        }
        return checkWin;
    }

    }


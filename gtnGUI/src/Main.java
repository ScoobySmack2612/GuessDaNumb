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

public class Main extends Application {

    Stage window;
    Scene menu, game1;
    Button button;
    Button gameButton;

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
        button.setOnAction(e -> window.setScene(game1));

        HBox menu1 = new HBox(20);
        menu1.getChildren().addAll(welcome,start);

        StackPane menu2 = new StackPane();
        menu2.getChildren().add(button);

        BorderPane grandeScheme = new BorderPane();
        grandeScheme.setTop(menu1);
        grandeScheme.setCenter(menu2);

        menu = new Scene(grandeScheme,450,400);


        //scene 2 - Game1
        Label gameText = new Label("Enter a number!");

        gameButton = new Button("Go Back!");
        gameButton.setOnAction(e -> window.setScene(menu));
        TextField userIn = new TextField();

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(gameText,userIn,gameButton);
        layout2.setAlignment(Pos.CENTER);
        game1 = new Scene(layout2,450,400);


        window.setScene(menu);
        window.show();
    }
}

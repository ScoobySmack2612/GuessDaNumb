import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.application.Application.launch;

/**
 * Created by Heron on 2/14/2017.
 */
public class gtnRedo extends Application {

    Stage window;
    Scene menu, game1;
    Button menuBtn, submitBtn;
    int guesses = 0;

    public static void Main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Set Window and title
        window = primaryStage;
        window.setTitle("Welcome to gtn");


        //Background one - bg1 - labels
        Label menuL = new Label("Hello, Welcome");
        Label start = new Label("click below!");

        //Background one - bg1 - button(s) && actions
        menuBtn = new Button("Start!");
        menuBtn.setOnAction(e -> {
            window.setScene(game1);
        });

        //Background one - bg1 - Display layout
        VBox bg1 = new VBox(50);
        bg1.getChildren().addAll(menuL, start, menuBtn);

        //Background one - bg1 - Scene && layout set
        menu = new Scene(bg1,250,250);
        bg1.setAlignment(Pos.CENTER);

        //Background two - bg2 - Labels
        Label game = new Label("enter a numb yo");
        Label game2 = new Label("Hit submit yo");

        //Generate Random Number
        Random rand = new Random();
        int theNumb = rand.nextInt(51);

        //Background two - bg2 - button(s) and stuff
        submitBtn = new Button("Submit");
        TextField userIn = new TextField();
        submitBtn.setOnAction(e -> {
            try{
                int userGuess = Integer.parseInt(userIn.getText());
                guesses ++;
                logic(userGuess, theNumb, guesses);
            }catch(NumberFormatException exception){
                System.out.println("Error: "+userIn.getText()+" is not an integer");
            }

        });


        //Background two - bg2 - layout
        VBox bg2 = new VBox(20);
        bg2.getChildren().addAll(game,userIn,submitBtn,game2);


        //Background two - bg2 - scene && layout set
        game1 = new Scene(bg2,250,250);
        bg2.setAlignment(Pos.CENTER);

        //Show window with default scene
        window.setScene(menu);
        window.show();
    }
    private static void logic(int guess, int theNumb, int guesses){
        int run = 5-guesses;
        if (run==0 && guess != theNumb){
            System.out.println("Loser!");
        }else if (run==0 && guess == theNumb){
            System.out.println("Winner!");
        }else{
            if (guess == theNumb) {
                System.out.println("Winner");
                System.out.println(run);
            } else if (guess > theNumb) {
                if (guess > 50) {
                    System.out.println("Way too high... like out of scope you wtf");
                } else {
                    System.out.println("Too high, back on down friend.");
                    System.out.println(run);
                }
            } else if (guess < theNumb) {
                if (guess < 0) {
                    System.out.println("Dude come on stay in range.");
                } else {
                    System.out.println("Too low!!! Try Again :)");
                    System.out.println(run);
                }
            }
        }
    }
}

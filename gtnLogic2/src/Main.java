import java.util.Random;
import java.util.Scanner;

/**
 * Created by Heron on 2/17/2017.
 */
public class Main {

    public static void main(String[] args){
        Boolean program = true;
        while(program){
            Boolean play = askToPlay();
            if (play == true) {
                //start Logic
                int theNumb = getTheNumb();
                System.out.println("Pick a number between 0 and 50.");
                for (int guesses = 0; guesses <= 5; guesses++) {
                    //Call user input method
                    int userGuess = getIn();
                    //compare userGuess and range of theNumb
                    String checked = rangeCheck(userGuess, theNumb);
                    if (guesses == 5 && checked.contains("Congratulations")) {
                        System.out.println(checked);
                        break;
                    } else if (guesses == 5 && checked.contains("low") || guesses == 5 && checked.contains("high") || guesses == 5 && checked.contains("range")) {
                        System.out.println("Im sorry you lost :'( but don't give up! try again!");
                        break;
                    } else {
                        //result messages was wrong or won
                        if (checked.contains("low") || checked.contains("high") || checked.contains("range")) {
                            System.out.println("you guessed " + userGuess + " which is " + checked + " you have " + (4 - guesses) + " guesses left.");
                        } else if (checked.contains("Congratulations")) {
                            System.out.println(checked);
                            break;
                        }
                    }
                }
            }else {
                System.out.println("Sorry to see you go :'(");
                program = false;
            }
        }

    }

    private static Boolean askToPlay(){
        Boolean run = true;
        Boolean play;
        while(run){
            String ask = "Would you like to play Guess the Number? y/n?";
            System.out.println(ask);

            Scanner scan = new Scanner(System.in);
            String answer = scan.nextLine();

            if (answer.equals("y")){
                play = true;
                return play;
            }else if (answer.equals("n")){
                play = false;
                return play;
            }
            else{
                System.out.println("Please input 'y' or 'n'...");
            }
        }
        play = true;
        return play;
    }
    private static int getTheNumb(){
        Random rand = new Random();
        int theNumb = rand.nextInt(51);

        return theNumb;
    }
    private static int getIn(){
        Boolean gettingIn = true;
        int userGuess;
        Scanner input = new Scanner(System.in);
        while(gettingIn) {
            try {
                userGuess = Integer.parseInt(input.nextLine());
                return userGuess;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number...");
            }
        }
        userGuess = -1;
        return userGuess;
    }
    private static String rangeCheck(int userGuess, int theNumb){
        String checked;
        if (userGuess < 0 || userGuess > 50){
            checked = "out of range please pick a number between 0 and 50";
            return checked;
        }else if (userGuess > theNumb){
            checked = "too high";
            return checked;
        }else if (userGuess < theNumb){
            checked = "too low";
            return checked;
        }else if (userGuess == theNumb){
            checked = "Congratulations you're a wee-nor!";
            return checked;
        }
        checked = "ERROR: error no. 43 - who even frikin knows...";
        return checked;
    }
}

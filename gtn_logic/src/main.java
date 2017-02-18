/**
 * Created by Heron on 2/9/2017.
 */

import java.util.Scanner;
import java.util.Random;
import java.util.StringJoiner;

class Main
    {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            Scanner scanInput = new Scanner(System.in);
            Boolean run = true;

            while (run) {
                String gtn = "Wanna play a Game? Im thinking Guess the Number... y/n:";
                System.out.println(gtn);

                String play = scanInput.nextLine();

                Boolean letsPlay;
                Boolean deciding = true;

                while (deciding) {
                    if (play.equals("y")) {
                        letsPlay = true;

                        Random rand = new Random();
                        int chosen;
                        chosen = rand.nextInt(51);
                        while (letsPlay) {
                            int lives;
                            int userGuess;
                            String numberComp;
                            try {
                                System.out.println("Please enter number from 0 to 50. you have 5 guesses.");
                                userGuess = Integer.parseInt(scan.nextLine());

                                System.out.println("Youre playing");

                                System.out.println("you chose: " + userGuess);

                                numberComp = compInt(userGuess, chosen);

                                for (lives = 5; lives >= 0; lives -= 1) {
                                    if (lives == 0 && numberComp.equals("Winner!")) {
                                        System.out.println("Won on fifth try");
                                    } else if (lives == 0 && !numberComp.equals("Winner")) {
                                        System.out.println("You lost... But don't give up! Try Again!");
                                        break;
                                    } else {
                                        if (numberComp.equals("Winner!")) {
                                            System.out.println(numberComp);
                                            break;
                                        } else {

                                            System.out.println("Sorry, but that answer was" + numberComp + " you have " + lives + " lives left.");
                                            userGuess = Integer.parseInt(scan.nextLine());
                                            numberComp = compInt(userGuess, chosen);
                                        }
                                    }
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Please enter integer");

                            }
                        }
                        break;
                    } else if (play.equals("n")) {
                        break;
                    } else {
                        System.out.println("Sorry please enter valid input");
                    }
                }

                System.out.println("You chose not to play :'(");
            }
        }

        private static String compInt(int userGuess, int chosen){
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
    }


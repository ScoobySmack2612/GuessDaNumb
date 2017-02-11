/**
 * Created by Heron on 2/9/2017.
 */

import java.util.Scanner;
import java.util.Random;
import java.util.StringJoiner;

class Main
    {

        public static void main(String[] args)
        {
            int lives = 5;
            String gtn = "Wanna play a Game? Im thinking Guess the Number... y/n:";
            Scanner scanInput = new Scanner(System.in);
            System.out.println(gtn);
            String play = scanInput.nextLine();

            while(play.equals("y"))
            {
                Random rand = new Random();
                int chosen = rand.nextInt(51);
                int userGuess = Integer.parseInt(scanInput.nextLine());
                String numberComp = compInt(userGuess, chosen);


                for(lives = 4; lives>=0; lives-=1) {
                    if(lives == 0 && numberComp.equals("Winner!")){
                        System.out.println("Won on fifth try");
                    }
                    else if(lives ==0 && !numberComp.equals("Winner")){
                        break;
                    }
                    else {
                        if (numberComp.equals("Winner!")) {
                            System.out.println(numberComp);
                            break;
                        } else {

                            System.out.println("Sorry, but that answer was" + numberComp + " you have " + lives + " lives left.");
                            userGuess = Integer.parseInt(scanInput.nextLine());
                            numberComp = compInt(userGuess, chosen);
                        }
                    }
                }

                System.out.println(gtn);
                play = scanInput.nextLine();
            }
            if(play.equals("n"))
            {
                System.out.println("You chose to not play the game :'(");
            }
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
    }


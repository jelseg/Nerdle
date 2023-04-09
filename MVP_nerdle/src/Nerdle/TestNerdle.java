package Nerdle;

import Nerdle.Model.Combination;
import Nerdle.Model.Difficulty;
import Nerdle.Model.Nerdle;
import Nerdle.Model.User;

import java.util.List;
import java.util.Scanner;

public class TestNerdle {

    public static void main(String[] args) {

        //System.out.println("your current score is: " + user.getScore(Difficulty.NORMAL));
        //List<User> allUsers = User.getAllUsers();
        User user = new User("yelle");

        System.out.println("hello " + user.getName());
        System.out.println("your score up till now is: " + user.getScore());

        Nerdle spel = new Nerdle(user,Difficulty.NORMAL);
        Scanner scanner = new Scanner(System.in);

        if(spel.getCurrentGuessNumber() != 0) {
            printGuesses(spel);
        }

        while (! spel.isOver()){
            System.out.println("next guess?");
            String input = scanner.nextLine();
            addString(input, spel);
            spel.enterCurrentGuess();

            printGuesses(spel);
            //needed in case guess is illegal, in real application this should be omitted (current guess should still show)
            spel.emptyCurrentGuess();

        }

        if(spel.isFoundIt()){
            System.out.println("proficiat, u hebt gewonnen");
        }
        else {
            System.out.println("loooooooooooooooooooooooooooooseeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrr");
        }
    }

    public static void addString(String s, Nerdle spel) {

        for (char c : s.toCharArray()){
            spel.addToCurrentGuess(c);
        }
    }

    public static void printGuesses(Nerdle spel){
        List<Combination> guessed = spel.getGuesses();

        System.out.println("You have already guessed:");
        System.out.println();
        for (Combination guess: guessed){
            System.out.println(guess);

            for (int i = 0; i < 8; i++){
                System.out.print("-");
            }
            System.out.println();
        }

        System.out.println("Still possible:");
        System.out.println();
        System.out.println(spel.getOverzicht());
    }
}

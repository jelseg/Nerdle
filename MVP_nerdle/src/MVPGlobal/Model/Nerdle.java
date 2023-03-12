package Nerdle.Model;

import java.util.List;

public class Nerdle {

    private static final int MAX_NUMBER_OF_GUESSES = 6;
    private Combination[] guesses;
    private int currentGuessNumber;
    private Combination currentGuess;
    private Answer answer;
    private boolean foundIt;
    private Overzicht overzicht;
    //User currentUser

    public Nerdle(){
        guesses = new Combination[MAX_NUMBER_OF_GUESSES];
        for (int i = 0; i < MAX_NUMBER_OF_GUESSES; i++){
            guesses[i] = new Combination();
        }
        currentGuessNumber = 0;

        currentGuess = new Combination();

        answer = new Answer();
        foundIt = false;
        overzicht = new Overzicht();
    }

    public boolean addToCurrentGuess(EquationCharacter c){
        return currentGuess.addToEnd(c);
    }
    public boolean addToCurrentGuess(char c){
        return currentGuess.addToEnd(c);
    }

    public boolean deleteFromCurrentGuess(){
        return currentGuess.deleteLast();
    }

    //returns true if guess is processed
    public boolean enterCurrentGuess(){
        // PLACEHOLDER
        if (currentGuess.isLegal() && currentGuessNumber < MAX_NUMBER_OF_GUESSES) {
            foundIt = currentGuess.compare(answer);
            guesses[currentGuessNumber] = currentGuess;
            currentGuessNumber++;
            currentGuess = new Combination();
            return true;
        }
        return false;
    }

    public boolean isOver(){
        if (foundIt || currentGuessNumber >= MAX_NUMBER_OF_GUESSES){
            return true;
        }
        return false;
    }


    // getters and setters


    public Combination[] getGuesses() {
        return guesses;
    }

    public Combination getCurrentGuess() {
        return currentGuess;
    }

    public Answer getAnswer() {
        return answer;
    }

    public boolean isFoundIt() {
        return foundIt;
    }

    public Overzicht getOverzicht() {
        return overzicht;
    }
}

package Nerdle.Model;

import java.util.LinkedList;
import java.util.List;

public class Nerdle {

    //private static final int MAX_NUMBER_OF_GUESSES = 6;
    private List<Combination> guesses;
    private int currentGuessNumber;
    private Combination currentGuess;
    private Answer answer;
    private boolean foundIt;
    private Overzicht overzicht;
    //User currentUser

    private Difficulty difficulty;

    public Nerdle() throws NerdleException{

        difficulty = Difficulty.NORMAL;

        guesses = new LinkedList<>();

        currentGuessNumber = 0;

        currentGuess = new Combination(difficulty);

        answer = new Answer(difficulty);
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
        if (currentGuess.isLegal() && currentGuessNumber < difficulty.getnTries()) {
            foundIt = currentGuess.compare(answer);
            guesses.add(currentGuess);
            overzicht.updateColors(currentGuess);
            currentGuessNumber++;
            currentGuess = new Combination(difficulty);
            return true;
        }
        return false;
    }

    public void emptyCurrentGuess(){
        currentGuess = new Combination(difficulty);
    }

    public boolean isOver(){
        if (foundIt || currentGuessNumber >= difficulty.getnTries()){
            return true;
        }
        return false;
    }


    // getters and setters


    public List<Combination> getGuesses() {
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

    public int getCurrentGuessNumber() { return currentGuessNumber;}
}

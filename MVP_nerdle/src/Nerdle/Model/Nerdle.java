package Nerdle.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Nerdle {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    //private static final int MAX_NUMBER_OF_GUESSES = 6;
    private List<Combination> guesses;
    //private int currentGuessNumber;
    private Combination currentGuess;
    private Answer answer;
    private boolean foundIt;
    private Overzicht overzicht;
    //User currentUser

    private Difficulty difficulty;

    private User user;

    public Nerdle() throws NerdleException{

        difficulty = Difficulty.NORMAL;

        guesses = new LinkedList<>();

        //currentGuessNumber = 0;

        currentGuess = new Combination(difficulty);

        answer = new Answer(difficulty);
        foundIt = false;
        overzicht = new Overzicht();
    }

    public Nerdle(User user,Difficulty difficulty) throws NerdleException{
        this.difficulty = difficulty;

        guesses = new LinkedList<>();

        //currentGuessNumber = 0;

        currentGuess = new Combination(difficulty);

        answer = new Answer(difficulty);
        foundIt = false;
        overzicht = new Overzicht();

        this.user = user;
        loadSaveGame();
    }

    private void loadSaveGame() throws NerdleException{
        //allign with saveGame
        File saveFile = user.getLastGameFile(difficulty);

        if (saveFile.exists()){

            try(BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
                //first line is the date of the save game. Only if it is today should it be loaded
                String line = reader.readLine();
                if(line.equals(LocalDate.now().format(DATE_FORMAT))){
                    while((line = reader.readLine()) != null){

                        //add each line to the guesses, the same way a player does (but never save the game at the end)
                        for (char c: line.toCharArray()){
                            addToCurrentGuess(c);
                        }
                        if(! enterCurrentGuess(false)){
                            setErrorCombo();
                            throw new NerdleException("The save file contains an illegal equation equation. " +
                                    "It might be corrupt");
                        }
                    }
                }
            }
            catch(IOException exception){
                //end the game with some standard output
                setErrorCombo();
                throw new NerdleException("Could not load your previous game. File might be corrupt",exception);
            }


        }
    }
    private void setErrorCombo(){
        Combination defaultCombo = new Combination(difficulty);
        EquationCharacter defaultCharacter = new EquationCharacter(0);
        defaultCharacter.setColor(EquationCharacter.CombinationColor.BLACK);
        for (int i = 0; i < difficulty.getComboLength(); i++){
            defaultCombo.addToEnd(defaultCharacter);
        }

        for (int i = getCurrentGuessNumber(); i < difficulty.getnTries();i++){
            guesses.add(defaultCombo);
        }
    }

    public void saveGame() throws NerdleException {
        //align with loadSaveGame

        if(user != null){
            File saveFile = user.getLastGameFile(difficulty);

            try(BufferedWriter writer = new BufferedWriter( new FileWriter(saveFile,false))){
                writer.write(LocalDate.now().format(DATE_FORMAT));
                writer.newLine();

                for(Combination combo: guesses){
                    writer.write(combo.getCombinationString());
                    writer.newLine();
                }
            }
            catch(IOException exception){
                throw new NerdleException("Could not save your game",exception);
            }

        }
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
        return enterCurrentGuess(true);
    }

    // used in initialisation, if loading a save game that has been completed there should not be a new Result saved
    //for the user. For users of the function saveIfEnd should always be true so they can only use the version without
    //argument.
    private boolean enterCurrentGuess(boolean saveIfEnd){
        if (currentGuess.isLegal() && getCurrentGuessNumber() < difficulty.getnTries()) {
            foundIt = currentGuess.compare(answer);
            guesses.add(currentGuess);
            overzicht.updateColors(currentGuess);
            //CurrentGuessNumber++;
            currentGuess = new Combination(difficulty);

            if(user != null && isOver() && saveIfEnd){
                user.saveResult(answer,difficulty,foundIt?getCurrentGuessNumber():getCurrentGuessNumber()+1);
                saveGame();
            }
            return true;
        }
        return false;
    }

    public void emptyCurrentGuess(){
        currentGuess = new Combination(difficulty);
    }

    public boolean isOver(){
        if (foundIt || getCurrentGuessNumber() >= difficulty.getnTries()){
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

    public int getCurrentGuessNumber() { //return currentGuessNumber;
        return guesses.size(); }
}

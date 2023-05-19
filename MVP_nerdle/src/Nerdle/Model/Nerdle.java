package Nerdle.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class of the game, containing all elements of the game
 */
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

    private SavedGames savedGames;

    /**
     * @deprecated use version with user and difficulty
     * @throws NerdleException
     */
    public Nerdle() throws NerdleException{

        difficulty = Difficulty.NORMAL;

        guesses = new LinkedList<>();

        //currentGuessNumber = 0;

        currentGuess = new Combination(difficulty);

        answer = new Answer(difficulty);
        foundIt = false;
        overzicht = new Overzicht(difficulty);
    }

    /**
     *
     * @param user
     * @param difficulty
     * @throws NerdleException for problems opening any save or config file
     */
    public Nerdle(User user,Difficulty difficulty) throws NerdleException{
        this.difficulty = difficulty;

        this.savedGames = new SavedGames(user,difficulty);

        guesses = new LinkedList<>();

        //currentGuessNumber = 0;

        currentGuess = new Combination(difficulty);

        answer = new Answer(difficulty);
        foundIt = false;
        overzicht = new Overzicht(difficulty);

        this.user = user;
        loadSaveGame();
    }

    /**
     * checks if there is a saved game for the user
     * if the saved game is of today, it loads this game
     * @throws NerdleException
     */
    private void loadSaveGame() throws NerdleException{

        if(user != null) {
            String[] savedGuesses = savedGames.getLastSavedGame();

            for (String s : savedGuesses) {
                for (char c : s.toCharArray()) {
                    addToCurrentGuess(c);
                }
                if (!enterCurrentGuess(false)) {
                    setErrorCombo();
                    throw new NerdleException("The save file contains an illegal character. " +
                            "It might be corrupt");
                }

            }
        }

        /*
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
         */
    }

    /**
     * to be used when an error occurs to replace loaded guesses that couldn't be parsed
     */
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

    /**
     * saves the users game
     */
    public void saveGame() throws NerdleException {
        //align with loadSaveGame

        if(user != null){
            /*
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
             */

            savedGames.saveGame(guesses);

        }
    }

    /**
     * add an EquationCharacter to the end of current guess
     * @param c EquationCharacter
     * @return true when the character was added successfully
     */
    public boolean addToCurrentGuess(EquationCharacter c){
        if (!isOver()) {
            return currentGuess.addToEnd(c);
        }
        return false;
    }
    /**
     * add an EquationCharacter to the end of current guess
     * @param c +,-,*,/,= or 0-9
     * @return true when the character was added successfully
     */
    public boolean addToCurrentGuess(char c){
        if (!isOver()) {
            return currentGuess.addToEnd(c);
        }
        return false;
    }

    /**
     * removes the last EquationCharacter of the current guess
     * @return true when there was an EquationCharacter to remove
     */
    public boolean deleteFromCurrentGuess(){
        if (!isOver()) {
            return currentGuess.deleteLast();
        }
        return false;
    }

    //returns true if guess is processed

    /**
     * checks if the current guess is legal (else stops and returns false)
     * compares it with the correct Answer (this sets the colors of the guess)
     * update the Overzicht
     * adds it to end of guesses
     * empties the current guess
     * if the game ends it will save the game for the user
     * @return true if the current guess was added to the guesses
     */
    public boolean enterCurrentGuess(){
        return enterCurrentGuess(true);
    }

    // used in initialisation, if loading a save game that has been completed there should not be a new Result saved
    //for the user. For users of the function saveIfEnd should always be true so they can only use the version without
    //argument.

    /**
     * added this extra function to be used when loading a saved game (should not be saved again when loading a
     * finished game)
     * @param save True -> if the CurrentGuess is legal, it will save the game and, when the game is finished, adds the score
     * @return true if the current guess was added to the guesses
     */
    private boolean enterCurrentGuess(boolean save){
        if (currentGuess.isLegal() && getCurrentGuessNumber() < difficulty.getnTries()) {
            foundIt = currentGuess.compare(answer);
            guesses.add(currentGuess);
            overzicht.updateColors(currentGuess);
            //CurrentGuessNumber++;
            currentGuess = new Combination(difficulty);

            if(user != null && save){
                if(isOver()) {
                    user.saveResult(answer, difficulty, foundIt ? getCurrentGuessNumber() : getCurrentGuessNumber() + 1);
                }
                saveGame();
            }
            return true;
        }
        return false;
    }

    public void emptyCurrentGuess(){
        currentGuess = new Combination(difficulty);
    }

    /**
     *
     * @return True when the game is over (won or max number of guesses reached)
     */
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

    /**
     *
     * @return true when the game has been won
     */
    public boolean isFoundIt() {
        return foundIt;
    }

    public Overzicht getOverzicht() {
        return overzicht;
    }

    public int getCurrentGuessNumber() { //return currentGuessNumber;
        return guesses.size(); }

    public EquationCharacter[] getLastGuessCharacters(){
        return guesses.get(guesses.size()-1).getCharacters();
    }
}

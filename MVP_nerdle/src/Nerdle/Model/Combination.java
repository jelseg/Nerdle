package Nerdle.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing one equation consisting of multiple EquationCharacters.
 *
 */
public class Combination {

    //protected static int COMBINATION_SIZE = 8;

    protected Difficulty difficulty;
    protected EquationCharacter[] characters;
    private int currentPos;

    /**
     *
     * creates a new empty combination to be filled
     *
     * @param difficulty A difficulty deciding the number of EquationCharacters in the Combination
     */
    public Combination(Difficulty difficulty){
        this.difficulty = difficulty;
        characters = new EquationCharacter[difficulty.getComboLength()];
        for (int i =0; i < difficulty.getComboLength(); i++){
            characters[i] = new EquationCharacter();
        }
        currentPos = 0;
    }

    /**
     * chacks if the current equation is legal
     * meaning:
     *          all EquationCharacters are filled in
     *          the is exactly one =
     *          when calculated the left side of the equation equals the right side
     * @return true when the equation in the combination is legal
     */
    public boolean isLegal(){


        List<EquationCharacter> leftOfEqual = new LinkedList<>();
        List<EquationCharacter> rightOfEqual = new LinkedList<>();

        boolean afterEqual = false;
        boolean hasIllegalchars = false;
        boolean lastWasOperator = true;

        for( EquationCharacter c:characters){
            switch (c.getOperation()){
                case EMPTY: hasIllegalchars = true; break;
                case EQUALS:
                    if(afterEqual||lastWasOperator) {
                        hasIllegalchars = true;
                    }
                    else {
                        afterEqual = true;
                    }
                    lastWasOperator = true;
                    break;
                case NUMBER:
                    lastWasOperator = false;
                    if (afterEqual){
                        rightOfEqual.add(c);
                    }
                    else {
                        leftOfEqual.add(c);
                    }
                    break;
                default:
                    if (lastWasOperator) {
                        hasIllegalchars = true;
                    }
                    else {
                        if (afterEqual) {
                            rightOfEqual.add(c);
                        }
                        else {
                            leftOfEqual.add(c);
                        }
                        lastWasOperator = true;
                    }
            }
        }

        if(!afterEqual || hasIllegalchars || lastWasOperator){
            return false;
        }
        else {
            return calculate(leftOfEqual) == calculate(rightOfEqual);
        }
    }

    /**
     * parses an array of equation characters to the correct number
     * @param chars array of Equation characters (no =) representing a formula
     * @return the result of the calculations
     */
    double calculate(List<EquationCharacter> chars){
        double d = calculateSums(chars);
        //System.out.println(d);
        return d;
    }

    /**
     * helper function for calculate
     * splits the array of characters on + and -, calls the calculateMultiply function on the parts and oes the sums
     * and substractions
     */
    private double calculateSums(List<EquationCharacter> chars){
        List<EquationCharacter> currentPart = new LinkedList<>();

        double tot = 0;
        EquationCharacter.Operation lastOp = EquationCharacter.Operation.PLUS;

        for(EquationCharacter c: chars){
            if (c.getOperation() == EquationCharacter.Operation.PLUS || c.getOperation() == EquationCharacter.Operation.MINUS){
                double currentNumb = calculateMultiply(currentPart);
                switch(lastOp){
                    case PLUS: tot += currentNumb; break;
                    case MINUS: tot -= currentNumb; break;
                }
                lastOp = c.getOperation();
                currentPart = new LinkedList<>();
            }
            else {
                currentPart.add(c);
            }
        }
        double currentNumb = calculateMultiply(currentPart);
        switch(lastOp){
            case PLUS: tot += currentNumb; break;
            case MINUS: tot -= currentNumb; break;
        }
        return tot;
    }


    //assumes there are only NUMBER, MULTIPLY and DIVIDE characters in chars (should only be called by calculateSums)

    /**
     *  helper function for calculate
     * @param chars should only contain numbers, x and /
     */
    private double calculateMultiply(List<EquationCharacter> chars){

        double currentNumb = 0;
        double tot = 1;
        EquationCharacter.Operation lastOp = EquationCharacter.Operation.MULTIPLY;

        for(EquationCharacter c: chars){
            if(c.getOperation()== EquationCharacter.Operation.MULTIPLY || c.getOperation()== EquationCharacter.Operation.DIVIDE){
                switch(lastOp){
                    case MULTIPLY: tot *= currentNumb;break;
                    case DIVIDE: tot /= currentNumb;break;
                }
                currentNumb = 0;
                lastOp = c.getOperation();
            }
            else {
                currentNumb *=10;
                currentNumb += c.getNumber();
            }
        }

        switch(lastOp){
            case MULTIPLY: tot *= currentNumb;break;
            case DIVIDE: tot /= currentNumb;break;
        }

        return tot;

    }

    // sets the colors of the characters according (same as other -> green, in other -> purple, else Black)
    // return true if all correct

    /**
     *
     * compares this Combination with another one, setting the colors in this Combination
     *
     * @param other the correct Combination
     * @return true when all equationCharacters match
     * @throws NerdleException
     */
    public boolean compare(Combination other) throws NerdleException{

        if (other.getCombinationLength() != getCombinationLength()){
            throw new NerdleException("A problem occurred when comparing combinations. The sizes were not equal.");
        }

        // to make sure if your combination has a duplicate character and other has this character only once
        // the color will be set to purple for only one
        boolean[] otherUsed = new boolean[Difficulty.NORMAL.getComboLength()];

        boolean allCorrect = true;

        EquationCharacter[] otherChars = other.getCharacters();

        if(otherChars.length != difficulty.getComboLength() || this.characters.length != difficulty.getComboLength()){
            throw new RuntimeException("there has been a mismatch between the size of the combinations and the " +
                    "parameter COMBINATION_SIZE. Has the Combination size changed without rebuilding all combinations and answers?");
                    // should make a new exception for this
        }

        for (int i = 0; i < difficulty.getComboLength(); i++) {

            //is correct:
            if(this.characters[i].equals(otherChars[i])){
                this.characters[i].setColor(EquationCharacter.CombinationColor.GREEN);
                otherUsed[i] = true;
            }
            else {
                allCorrect = false;
                //chack to make purple:
                boolean foundOne = false;
                for(int j = 0; j < difficulty.getComboLength() && !foundOne; j++ ){
                    if(this.characters[i].equals(otherChars[j]) && !otherUsed[j] && !this.characters[j].equals(otherChars[j])){
                        this.characters[i].setColor(EquationCharacter.CombinationColor.PURPLE);
                        otherUsed[j] = true;
                        foundOne = true;
                    }
                }
                //else should be black
                if(!foundOne){
                    this.characters[i].setColor(EquationCharacter.CombinationColor.BLACK);
                }
            }
        }

        return allCorrect;
    }

    /**
     * adds an EquationCharacter to the end of the combination
     * @param c EquationCharacter to add
     * @return True when it could be added, False when the Combination is already full
     */
    public boolean addToEnd(EquationCharacter c){
        if ( currentPos < difficulty.getComboLength()) {
            characters[currentPos] = c;
            currentPos++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * adds an EquationCharacter to the end of the combination but with a char as input. Used for parsing Strings from
     * files or the consol/test version of the program
     *
     * @param c *,/,+,-,= or a number (0-9)
     * @return true when succesfully added, false when Combination is already full
     */
    public boolean addToEnd(char c){
        EquationCharacter.Operation ops = null;
        switch (c){
            case 'x':
            case '*': ops = EquationCharacter.Operation.MULTIPLY; break;
            case '/': ops = EquationCharacter.Operation.DIVIDE; break;
            case '+': ops = EquationCharacter.Operation.PLUS; break;
            case '-': ops = EquationCharacter.Operation.MINUS; break;
            case '=': ops = EquationCharacter.Operation.EQUALS; break;
        }
        EquationCharacter eqc = null;
        if (ops == null){
            if (c >= '0' && c <= '9') {
                int number = c - '0';
                eqc = new EquationCharacter(number);
            }
            else {
                throw new IllegalArgumentException("could not add " + c + " to the equation.");
            }
        }
        else {
            eqc = new EquationCharacter(ops);
        }

        return this.addToEnd(eqc);
    }

    /**
     * deletes the last EquationCharacter from the Combination
     *
     * @return True when there was an EquationCharacter to remove
     */
    public boolean deleteLast(){
        if (currentPos > 0 ) {
            currentPos--;
            characters[currentPos] = new EquationCharacter();
            return true;
        }
        else {
            return false;
        }
    }

    //getters and setters

    public EquationCharacter[] getCharacters() {
        return characters;
    }


    /**
     * used in the console/test version of the game
     * @return two line string with on first line the equation and on the second line symbols for the colors
     */
    @Override
    public String toString() {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for(EquationCharacter c : characters){
            s1.append(c.toString());
            s2.append(c.getColor());
        }
        return s1.append("\n").append(s2).toString();
    }

    /**
     * a string containing only the equation
     */
    public String getCombinationString(){
        StringBuilder s1 = new StringBuilder();
        for(EquationCharacter c : characters){
            s1.append(c.toString());
        }
        return s1.toString();
    }

    public int getCombinationLength(){
        return difficulty.getComboLength();
    }
}

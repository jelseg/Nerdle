package Nerdle.Model;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    protected static int COMBINATION_SIZE = 8;

    protected EquationCharacter[] characters;
    private int currentPos;

    public Combination(){
        characters = new EquationCharacter[COMBINATION_SIZE];
        for (int i =0; i < COMBINATION_SIZE; i++){
            characters[i] = new EquationCharacter();
        }
        currentPos = 0;
    }

    public boolean isLegal(){
        // placeholder
        return true;
    }

    // sets the colors of the characters according (same as other -> green, in other -> purple, else Black)
    // return true if all correct
    public boolean compare(Combination other){

        // to make sure if your combination has a duplicate character and other has this character only once
        // the color will be set to purple for only one
        boolean[] otherUsed = new boolean[COMBINATION_SIZE];

        boolean allCorrect = true;

        EquationCharacter[] otherChars = other.getCharacters();

        if(otherChars.length != COMBINATION_SIZE || this.characters.length != COMBINATION_SIZE){
            throw new RuntimeException("there has been a mismatch between the size of the combinations and the " +
                    "parameter COMBINATION_SIZE. Has the Combination size changed without rebuilding all combinations and answers?");
                    // should make a new exception for this
        }

        for (int i = 0; i < COMBINATION_SIZE; i++) {

            //is correct:
            if(this.characters[i].equals(otherChars[i])){
                this.characters[i].setColor(EquationCharacter.CombinationColor.GREEN);
                otherUsed[i] = true;
            }
            else {
                allCorrect = false;
                //chack to make purple:
                boolean foundOne = false;
                for(int j = 0; j < COMBINATION_SIZE && !foundOne; j++ ){
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

    public boolean addToEnd(EquationCharacter c){
        if ( currentPos < COMBINATION_SIZE) {
            characters[currentPos] = c;
            currentPos++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addToEnd(char c){
        OperationCharacter.Operation ops = null;
        switch (c){
            case 'x': ops = OperationCharacter.Operation.MULTIPLY; break;
            case '/': ops = OperationCharacter.Operation.DIVIDE; break;
            case '+': ops = OperationCharacter.Operation.PLUS; break;
            case '-': ops = OperationCharacter.Operation.MINUS; break;
            case '=': ops = OperationCharacter.Operation.EQUALS; break;
        }
        EquationCharacter eqc = null;
        if (ops == null){
            if (c >= '0' && c <= '9') {
                int number = c - '0';
                eqc = new NumberCharacter(number);
            }
            else {
                throw new IllegalArgumentException("could not add " + c + " to the equation.");
            }
        }
        else {
            eqc = new OperationCharacter(ops);
        }

        return this.addToEnd(eqc);
    }

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


    @Override
    public String toString() {

        String s1 = "";
        String s2 = "";

        for(EquationCharacter c : characters){
            s1 += c.toString();
            s2 += c.getColor();
        }
        return s1 + "\n" + s2;
    }
}

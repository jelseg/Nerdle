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
        // placeholder
        return false;
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

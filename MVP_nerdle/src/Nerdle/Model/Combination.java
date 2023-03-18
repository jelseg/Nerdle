package Nerdle.Model;

import java.util.ArrayList;
import java.util.LinkedList;
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


    public double calculate(List<EquationCharacter> chars){
        double d = calculateSums(chars);
        //System.out.println(d);
        return d;
    }
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

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for(EquationCharacter c : characters){
            s1.append(c.toString());
            s2.append(c.getColor());
        }
        return s1.append("\n").append(s2).toString();
    }
}

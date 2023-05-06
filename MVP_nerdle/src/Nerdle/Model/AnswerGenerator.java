package Nerdle.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AnswerGenerator {


    public static void main(String[] args) {

        Overzicht overzicht = new Overzicht();

        //int length = 8;
        Difficulty difficulty = Difficulty.NORMAL;

        String answersFileName = Answer.getAllAnswerFile(difficulty);

        try (PrintWriter pW = new PrintWriter(new BufferedWriter(new FileWriter(answersFileName)))) {
            findAllLegal(difficulty.getComboLength(), overzicht, new Combination(difficulty),true,pW,0);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    //returns next id
    public static int findAllLegal(int LtoGo, Overzicht overzicht, Combination currentCombo, boolean lastWasOperator, PrintWriter pW, int lastId){

        if (LtoGo <= 0){
            if (currentCombo.isLegal() && extraTest(currentCombo)){
                System.out.println(currentCombo.getCombinationString());
                pW.format(Answer.ALL_ANSWERS_FORMAT,lastId,currentCombo.getCombinationString());
                return ++lastId;
            }
        }
        else {
            for (int i = 0; i < Overzicht.N_POSSIBILITIES; i++){
                EquationCharacter c = overzicht.getPossibility(i);

                // we know that two operations after each other is always illegal => don't try
                // we don't want the answers like 01+02=03 (numbers should start with something else then 0) even
                // though these are legal combinations.
                if ((!lastWasOperator) || (c.getNumber() > 0)) {
                    currentCombo.addToEnd(c);
                    lastId = findAllLegal(LtoGo - 1, overzicht, currentCombo, c.getOperation() !=
                                EquationCharacter.Operation.NUMBER, pW,lastId);
                    currentCombo.deleteLast();
                }
            }
        }
        return lastId;

    }

    //filters out some unwanted possible answers
    public static boolean extraTest(Combination combo){
        // remove answers like 9=9x9-72 (number = formule), want to only keep 9x9-72=9 (formule = number) and (formule = formule)

        boolean isOK = true;
        boolean firstNumber = true;
        for (EquationCharacter c: combo.getCharacters()){
            if (firstNumber &&c.getOperation() == EquationCharacter.Operation.EQUALS){
                isOK = false;
            }

            if (c.getOperation() != EquationCharacter.Operation.NUMBER){
                firstNumber = false;
            }
        }

        return isOK;
    }
}

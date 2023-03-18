package Nerdle.Model;

public class AnswerGenerator {

    public static void main(String[] args) {

        Overzicht overzicht = new Overzicht();

        findAllLegal(8,overzicht, new Combination());

    }

    public static void findAllLegal(int LtoGo, Overzicht overzicht, Combination currentCombo){

        if (LtoGo <= 0){
            if (currentCombo.isLegal()){
                System.out.println(currentCombo);
            }
        }
        else {
            for (int i = 0; i < Overzicht.N_POSSIBILITIES; i++){
                EquationCharacter c = overzicht.getPossibility(i);

                currentCombo.addToEnd(c);
                findAllLegal(LtoGo-1,overzicht,currentCombo);
                currentCombo.deleteLast();
            }
        }

    }
}

package Nerdle.Model;

public class Overzicht {
    public static final int N_POSSIBILITIES = 15;

    private EquationCharacter[] possibilities;

    public Overzicht(){

        possibilities = new EquationCharacter[N_POSSIBILITIES];

        for (int i = 0; i < 10; i++) {
            possibilities[i] = new EquationCharacter(i);
        }

        possibilities[10] = new EquationCharacter( EquationCharacter.Operation.PLUS);
        possibilities[11] = new EquationCharacter(EquationCharacter.Operation.MINUS);
        possibilities[12] = new EquationCharacter(EquationCharacter.Operation.MULTIPLY);
        possibilities[13] = new EquationCharacter(EquationCharacter.Operation.DIVIDE);
        possibilities[14] = new EquationCharacter(EquationCharacter.Operation.EQUALS);
    }

    public void updateColors(Combination latestCombo){
        // PLACEHOLDER

        for (EquationCharacter cCombo: latestCombo.getCharacters()){

            for (EquationCharacter cOver:possibilities){

                if (cCombo.equals(cOver)){
                    switch (cCombo.getColor()){
                        case GREEN: cOver.setColor(EquationCharacter.CombinationColor.GREEN); break;
                        case PURPLE:
                            switch(cOver.getColor()){
                                case GREEN: break;
                                default: cOver.setColor(EquationCharacter.CombinationColor.PURPLE);
                            }
                            break;
                        case BLACK:
                            if (cOver.getColor() == EquationCharacter.CombinationColor.GRAY){
                                cOver.setColor(EquationCharacter.CombinationColor.BLACK);
                            }
                            break;
                    }
                }

            }

        }
    }

    public EquationCharacter getPossibility(int i) {
        return possibilities[i].copy();
    }

    @Override
    public String toString() {

        String s1 = "";
        String s2 = "";

        for(EquationCharacter c : possibilities){
            s1 += c.toString();
            s2 += c.getColor();
        }
        return s1 + "\n" + s2;
    }
}

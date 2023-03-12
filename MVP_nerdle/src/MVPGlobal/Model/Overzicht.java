package Nerdle.Model;

public class Overzicht {
    private static final int N_POSSIBILITIES = 15;

    private EquationCharacter[] possibilities;

    public Overzicht(){

        possibilities = new EquationCharacter[N_POSSIBILITIES];

        for (int i = 0; i < 10; i++) {
            possibilities[i] = new NumberCharacter(i);
        }

        possibilities[10] = new OperationCharacter( OperationCharacter.Operation.PLUS);
        possibilities[11] = new OperationCharacter(OperationCharacter.Operation.MINUS);
        possibilities[12] = new OperationCharacter(OperationCharacter.Operation.MULTIPLY);
        possibilities[13] = new OperationCharacter(OperationCharacter.Operation.DIVIDE);
        possibilities[14] = new OperationCharacter(OperationCharacter.Operation.EQUALS);
    }

    public void updateColors(Combination latestCombo){
        // PLACEHOLDER
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

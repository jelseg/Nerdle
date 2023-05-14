package Nerdle.Model;

/**
 * enum used to change the parameters of the game (comboLength and number of tries)
 */
public enum Difficulty {
    SUPER_EASY(6,8),EASY(6,6),NORMAL(8,6),HARD(10,6);

    private int comboLength;
    private int nTries;
    Difficulty(int comboLength,int nTries){
        this.comboLength = comboLength;
        this.nTries = nTries;
    }

    public int getComboLength() {
        return comboLength;
    }

    public int getnTries() {
        return nTries;
    }

    @Override
    public String toString() {
        if(this == SUPER_EASY){
            return "SUPER EASY";
        }
        return super.toString();
    }
}

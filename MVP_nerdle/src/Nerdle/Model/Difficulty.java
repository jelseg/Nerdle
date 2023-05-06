package Nerdle.Model;

/**
 * enum used to change the parameters of the game (comboLength and number of tries)
 */
public enum Difficulty {
    NORMAL(8,6);

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
}

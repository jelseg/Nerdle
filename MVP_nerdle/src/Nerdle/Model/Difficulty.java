package Nerdle.Model;

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

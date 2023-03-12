package Nerdle.Model;

public class NumberCharacter extends EquationCharacter{

    private int value;

    public NumberCharacter(int value){
        super();
        this.value = value;
    }

    public NumberCharacter(NumberCharacter other){
        super(other);
        this.value = other.getValue();
    }

    public int evaluate(int left){
        return left*10+value;
    }

    @Override
    public boolean equals(EquationCharacter other) {
        if(other instanceof NumberCharacter){
            return ((NumberCharacter) other).getValue() == this.value;
        }
        else {
            return false;
        }
    }


    //getters and setters

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public EquationCharacter copy() {
        return new NumberCharacter(this);
    }
}

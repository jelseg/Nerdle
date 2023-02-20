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

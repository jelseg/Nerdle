package Nerdle.Model;

public class EquationCharacter {

    public static enum CombinationColor {
        GRAY(' '),GREEN('+'),PURPLE('?'),BLACK('x');

        CombinationColor(char c){
            this.cRepresent = c;
        }

        private char cRepresent;

        @Override
        public String toString() {
            return ""+cRepresent;
        }
    }

    public static enum Operation{
        PLUS,MINUS,MULTIPLY,DIVIDE,EQUALS,NUMBER,EMPTY
    }

    private CombinationColor color;
    private Operation operation;
    int number;

    public EquationCharacter(){
        color = CombinationColor.GRAY;
        this.operation = Operation.EMPTY;
        number = -1;
    }

    public EquationCharacter(Operation operation){
        if (operation == Operation.NUMBER) {
            throw new IllegalArgumentException("For operation NUMBER use the contructor EquationCharacter(int)");
        }
        color = CombinationColor.GRAY;
        this.operation = operation;
        number = -1;
    }

    public EquationCharacter(int number){
        if (number < 0 || number > 9){
            throw new IllegalArgumentException("An equationcharacter can only have a number from 0 to 9");
        }

        color = CombinationColor.GRAY;
        this.operation = Operation.NUMBER;
        this.number = number;
    }

    public EquationCharacter(EquationCharacter other){
        this.color = other.getColor();
        this.operation = other.operation;
        this.number = other.number;
    }

    public boolean equals(EquationCharacter other){

        return this.number == other.number && this.operation == other.operation;
    }


    //getters and setters

    public CombinationColor getColor() {
        return color;
    }

    public void setColor(CombinationColor color) {
        this.color = color;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString(){

        switch (operation){
            case NUMBER: return this.number + "";
            case EMPTY: return " ";
            case EQUALS: return "=";
            case PLUS: return "+";
            case MINUS: return "-";
            case DIVIDE: return "/";
            case MULTIPLY: return "x";
        }

        return " ";
    }

    public EquationCharacter copy(){
        return new EquationCharacter(this);
    }
}

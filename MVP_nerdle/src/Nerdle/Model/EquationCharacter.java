package Nerdle.Model;

/**
 * Class representing one symbol of an equation and a color (used for marking it as correct, etc)
 */
public class EquationCharacter {

    /**
     * enum representing the color of the EquationCharacter -> Correct, wrong place, wrong,not checked
     */
    public static enum CombinationColor {
        GRAY(' '),GREEN('+'),PURPLE('?'),BLACK('x');

        CombinationColor(char c){
            this.cRepresent = c;
        }

        private char cRepresent;

        /**
         * used in the toString method of Combination for the console/test version of the game
         * @return single character representation
         */
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
    /**
     * When operation = = Number -> the number
     * else -> -1
     */
    int number;

    /**
     * creates an empty EquationCharacter
     */
    public EquationCharacter(){
        color = CombinationColor.GRAY;
        this.operation = Operation.EMPTY;
        number = -1;
    }

    /**
     * constructor for operations
     * @param operation PLUS,MINUS,MULTIPLY,DIVIDE or EQUALS
     */
    public EquationCharacter(Operation operation){
        if (operation == Operation.NUMBER) {
            throw new IllegalArgumentException("For operation NUMBER use the contructor EquationCharacter(int)");
        }
        color = CombinationColor.GRAY;
        this.operation = operation;
        number = -1;
    }

    /**
     * constructor for Numbers
     * @param number
     */
    public EquationCharacter(int number){
        if (number < 0 || number > 9){
            throw new IllegalArgumentException("An equationcharacter can only have a number from 0 to 9");
        }

        color = CombinationColor.GRAY;
        this.operation = Operation.NUMBER;
        this.number = number;
    }

    /**
     * copy constructor
     * @param other
     */
    public EquationCharacter(EquationCharacter other){
        this.color = other.getColor();
        this.operation = other.operation;
        this.number = other.number;
    }

    /**
     *
     * @param other
     * @return true when the operation and number are the same
     */
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

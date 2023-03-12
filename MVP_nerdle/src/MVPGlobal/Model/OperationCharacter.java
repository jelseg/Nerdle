package Nerdle.Model;

public class OperationCharacter extends EquationCharacter{

    public static enum Operation{
        PLUS,MINUS,MULTIPLY,DIVIDE,EQUALS
    }

    private Operation operation;

    public OperationCharacter(Operation operation){
        super();
        this.operation = operation;
    }

    public OperationCharacter(OperationCharacter other){
        super(other);
        this.operation = other.operation;
    }

    public int evaluate(int left, int right){

        switch (operation){
            case PLUS: return left+right;
            case MINUS: return left-right;
            case MULTIPLY: return  left*right;
            case DIVIDE:return left/right; //placeholder, should do check if result is int
            case EQUALS: return right-left;
        }

        return -1;
    }

    @Override
    public boolean equals(EquationCharacter other) {
        if(other instanceof OperationCharacter){
            return ((OperationCharacter) other).operation == this.operation;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        switch(operation){
            case DIVIDE: return "/";
            case MULTIPLY: return "x";
            case PLUS: return "+";
            case MINUS: return "-";
            case EQUALS: return "=";
        }
        return "?";
    }

    @Override
    public EquationCharacter copy() {
        return new OperationCharacter(this);
    }
}

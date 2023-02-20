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

    CombinationColor color;

    public EquationCharacter(){
        color = CombinationColor.GRAY;
    }

    public EquationCharacter(EquationCharacter other){
        this.color = other.getColor();
    }

    public CombinationColor getColor() {
        return color;
    }

    public void setColor(CombinationColor color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return ".";
    }

    public EquationCharacter copy(){
        return new EquationCharacter(this);
    }
}

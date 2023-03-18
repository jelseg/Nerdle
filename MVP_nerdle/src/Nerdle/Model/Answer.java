package Nerdle.Model;

import java.util.UUID;

public class Answer extends Combination{

    private UUID id;

    public Answer(){
        super();
        // loading and checks to be added
        addStringToCombo("40x3=120");
    }

    //returns true if string s is a valid equation and could be added as a combination
    private boolean addStringToCombo(String s){
        //placeholder
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            super.addToEnd(c);
        }
        return true;
    }

}

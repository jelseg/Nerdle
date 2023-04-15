package Nerdle.View.MainScreen;

import Nerdle.Model.EquationCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterTile extends ImageView {
    private Image image;

    public CharacterTile(){
        super();
        image = new Image("images/darkgrey.png");
        this.setImage(image);
    }

    public CharacterTile(EquationCharacter eqc){
        this();
        changeCharacter(eqc);
    }

    void changeCharacter( EquationCharacter eqc){

    }
}

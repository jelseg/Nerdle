package Nerdle.View.MainScreen;

import Nerdle.View.UISettings;
import javafx.scene.layout.GridPane;

public class GuessesView extends GridPane {

    UISettings uiSettings;


    CharacterTile[][] characterTiles;


    public GuessesView(UISettings uiSettings, int nGuesses, int guessLength) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }


    private void initialiseNodes() {
        //to be filled in
    }

    private void layoutNodes() {

        //to be filled in
    }

    CharacterTile getElement(int i, int j){
        //to be filled in
        return new CharacterTile();
    }
}

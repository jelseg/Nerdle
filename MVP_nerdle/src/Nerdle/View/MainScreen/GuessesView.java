package Nerdle.View.MainScreen;

import Nerdle.Model.Difficulty;
import Nerdle.Model.EquationCharacter;
import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GuessesView extends GridPane {

    UISettings uiSettings;


    //    CharacterTile[][] characterTiles;
    private int nGuesses;
    private int guessLength;
    //private CharacterTile blanco;
    private CharacterTile[][] guessesArray;

    public GuessesView(UISettings uiSettings, int nGuesses, int guessLength) {
        this.uiSettings = uiSettings;
        this.nGuesses = Difficulty.NORMAL.getnTries();
        this.guessLength = Difficulty.NORMAL.getComboLength();
        initialiseNodes();
        layoutNodes();
    }


    private void initialiseNodes() {
        //to be filled in
        //blanco = new Image("images/darkgrey.png");
        guessesArray = new CharacterTile[guessLength][nGuesses];

        for (int i = 0; i < guessLength; i++) {
            for (int j = 0; j < nGuesses; j++) {
                CharacterTile blanco = new CharacterTile(new EquationCharacter());
                guessesArray[i][j] = blanco;
            }
        }
    }

    private void layoutNodes() {
        for (int i = 0; i < guessLength; i++) {
            for (int j = 0; j < nGuesses; j++) {
                ImageView iv = guessesArray[i][j];
                add(iv,i,j);
                setMargin(iv, new Insets(5));
                //to be filled in
            }
        }
    }

    CharacterTile getElement(int i, int j) {
        //to be filled in
        return guessesArray[j][i];
    }

    public CharacterTile[][] getGuessesArray() {
        return guessesArray;
    }
}

package Nerdle.View.MainScreen;

import Nerdle.Model.EquationCharacter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class OverzichtView extends VBox {

    private HBox numbers;
    private HBox operations;

    private List<CharacterTile> lijst;

    private void initialiseNodes() {

        numbers = new HBox();
        operations = new HBox();
    }

    private void layoutNodes() {
        this.getChildren().addAll(numbers,operations);
    }

    CharacterTile addNumber(EquationCharacter eqc){
        CharacterTile characterTile = new CharacterTile(eqc);
        numbers.getChildren().add(characterTile);
        lijst.add(characterTile);
        return characterTile;
    }

    CharacterTile get(int i){
        return lijst.get(i);
    }
}

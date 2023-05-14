package Nerdle.View.MainScreen;

import Nerdle.Model.EquationCharacter;
import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class OverzichtView extends VBox {
    UISettings uiSettings;

    private HBox numbers;
    private HBox operations;

    private List<CharacterTile> lijst;

    private ImageView enterButton;
    private ImageView backSpaceButton;

    OverzichtView(UISettings uiSettings){
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
        setStyle("-fx-background-color: rgba(0,100,100,0.0)");

        lijst = new ArrayList();
    }

    private void initialiseNodes() {

        numbers = new HBox();
        operations = new HBox();

        enterButton = new ImageView("images/buttons/enter.png");
        backSpaceButton = new ImageView("images/buttons/delete.png");
        enterButton.setId("overviewbuttons");
        backSpaceButton.setId("overviewbuttons");
    }

    private void layoutNodes() {

        operations.getChildren().add(enterButton);
        operations.getChildren().add(backSpaceButton);

        this.getChildren().addAll(numbers,operations);
        setMargin(operations,new Insets(20));
        setPadding(new Insets(20));
        numbers.setAlignment(Pos.CENTER);
        operations.setAlignment(Pos.CENTER);
    }

    CharacterTile addNumber(EquationCharacter eqc){
        CharacterTile characterTile = new CharacterTile(eqc);
        characterTile.setId("overviewbuttons");
        numbers.getChildren().add(characterTile);
        lijst.add(characterTile);
        return characterTile;
    }

    CharacterTile addOperation(EquationCharacter eqc){
        CharacterTile characterTile = new CharacterTile(eqc);
        characterTile.setId("overviewbuttons");
        int index = operations.getChildren().size() - 2;
        operations.getChildren().add(index,characterTile);
        lijst.add(characterTile);
        return characterTile;
    }

    CharacterTile get(int i){
        return lijst.get(i);
    }

    ImageView getEnterButton() {
        return enterButton;
    }

    ImageView getBackSpaceButton() {
        return backSpaceButton;
    }
}

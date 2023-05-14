package Nerdle.View.NewGameScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.UISettings;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NewGameScreenView extends GridPane {

    private UISettings uiSettings;
    private Label chooseLabel;
    private Label newLabel;
    private Label difficultyLabel;

    private ComboBox<String> userBox;
    private ComboBox<Difficulty> difficultyComboBox;
    private TextField username;

    private Button playButton;
    private Button createButton;

    public NewGameScreenView(UISettings uiSettings){
        this.uiSettings=uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){

        chooseLabel = new Label("Choose a user:");
        newLabel =  new Label("New user?");

        difficultyLabel = new Label("Difficulty:");

        userBox = new ComboBox<>();
        difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll(Difficulty.values());
        difficultyComboBox.getSelectionModel().select(Difficulty.NORMAL);

        playButton = new Button("Play");
        createButton = new Button("Create and play");

        username = new TextField();

    }

    private void layoutNodes(){

        this.add(difficultyLabel,0,0);
        this.add(difficultyComboBox,1,0);

        this.add(chooseLabel,0,1);
        this.add(userBox,1,1);
        this.add(playButton,0,2,2,1);
        this.add(newLabel,0,3);
        this.add(username,1,3);
        this.add(createButton,0,4,2,1);
        setPrefWidth(uiSettings.getLowestRes() / 5);
        setPrefHeight(uiSettings.getLowestRes() / 5);
        setAlignment(Pos.CENTER);
        setHalignment(difficultyLabel,HPos.CENTER);
        setValignment(difficultyLabel,VPos.CENTER);
        setHalignment(difficultyComboBox,HPos.CENTER);
        setValignment(difficultyComboBox,VPos.CENTER);
        setHalignment(chooseLabel, HPos.CENTER);
        setValignment(chooseLabel, VPos.CENTER);
        setHalignment(userBox, HPos.CENTER);
        setValignment(userBox, VPos.CENTER);
        setHalignment(playButton, HPos.CENTER);
        setValignment(playButton, VPos.CENTER);
        setHalignment(newLabel, HPos.CENTER);
        setValignment(newLabel, VPos.CENTER);
        setHalignment(username, HPos.CENTER);
        setValignment(username, VPos.CENTER);
        setHalignment(createButton, HPos.CENTER);
        setValignment(createButton, VPos.CENTER);
        setVgap(10);
    }

    // to be called by presenter
    void addUsers(String[] names){
        userBox.getItems().addAll(names);
    }

    ComboBox<String> getUserBox(){ return userBox;}

    TextField getUsername() {
        return username;
    }

    Button getPlayButton() {
        return playButton;
    }

    Button getCreateButton() {
        return createButton;
    }

    ComboBox<Difficulty> getDifficultyComboBox(){return difficultyComboBox;}
}

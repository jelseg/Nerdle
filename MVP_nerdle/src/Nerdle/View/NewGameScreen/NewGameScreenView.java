package Nerdle.View.NewGameScreen;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NewGameScreenView extends GridPane {

    private Label chooseLabel;
    private Label newLabel;

    private ComboBox<String> userBox;
    private TextField username;

    private Button playButton;
    private Button createButton;

    public NewGameScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){

        chooseLabel = new Label("Choose a user:");
        newLabel =  new Label("New user?");

        userBox = new ComboBox<>();

        playButton = new Button("Play");
        createButton = new Button("Create and play");

        username = new TextField();

    }

    private void layoutNodes(){
        this.add(chooseLabel,0,0);
        this.add(userBox,1,0);
        this.add(playButton,0,1,1,1);
        this.add(newLabel,0,2);
        this.add(username,1,2);
        this.add(createButton,0,3,1,3);
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
}

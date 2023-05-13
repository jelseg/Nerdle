package Nerdle.View.MainMenuScreen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Button;

public class MainMenuScreenView extends GridPane {

    private ImageView nerdleLogo;
    private Button newGame, highScores, rules,about, settings, exit;
    public MainMenuScreenView(){
        initialiseNodes();
        layoutNodes();
    }
    private void initialiseNodes(){
        nerdleLogo=new ImageView(new Image("images/Nerdle_logo_backdrop.png"));
        newGame=new Button("New Game");
        highScores=new Button("Highscores");
        rules=new Button("Rules");
        settings=new Button("Settings");
        about=new Button("About");
        exit =new Button("Close");

    }
    private void layoutNodes(){
        this.setHgap(10);
        this.setVgap(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        nerdleLogo.setFitWidth(319);
        nerdleLogo.setFitHeight(210);
        newGame.setPrefWidth(200);
        highScores.setPrefWidth(200);
        rules.setPrefWidth(200);
        settings.setPrefWidth(200);
        about.setPrefWidth(200);
        exit.setPrefWidth(200);

        setHalignment(nerdleLogo,HPos.CENTER);
        setHalignment(newGame,HPos.CENTER);
        setHalignment(highScores,HPos.CENTER);
        setHalignment(rules,HPos.CENTER);
        setHalignment(settings,HPos.CENTER);
        setHalignment(about,HPos.CENTER);
        setHalignment(exit,HPos.CENTER);

        add(nerdleLogo,0,0);
        add(newGame,0,1);
        add(highScores,0,2);
        add(rules,0,3);
        add(settings,0,4);
        add(about,0,5);
        add(exit,0,6);

    }

    Button getNewGame() {
        return newGame;
    }

    Button getHighScores() {
        return highScores;
    }

    Button getRules() {
        return rules;
    }

    Button getAbout() {
        return about;
    }

    Button getSettings() {
        return settings;
    }

    Button getExit() {
        return exit;
    }
}

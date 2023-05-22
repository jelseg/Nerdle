package Nerdle.View.MainScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.MenuBarScreen.MenuBarPresenter;
import Nerdle.View.MenuBarScreen.MenuBarView;
import Nerdle.View.UISettings;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainScreenView extends BorderPane {

    UISettings uiSettings;

    GuessesView guessesView;
    OverzichtView overzichtView;

    MenuBarView menuBarView;

    Label messageLabel;

    public MainScreenView(UISettings uiSettings,Difficulty difficulty) {
        this.uiSettings = uiSettings;
        initialiseNodes(difficulty);
        layoutNodes();
    }

    private void initialiseNodes(Difficulty difficulty) {

        this.guessesView =new GuessesView(uiSettings, difficulty.getnTries(), difficulty.getComboLength());
        this.overzichtView = new OverzichtView(uiSettings);
        this.menuBarView = new MenuBarView(uiSettings);

        this.messageLabel = new Label("");


    }

    private void layoutNodes() {


        setTop(menuBarView);
        setCenter(new VBox(messageLabel,guessesView));
        setBottom(overzichtView);
        guessesView.setAlignment(Pos.CENTER);
        overzichtView.setAlignment(Pos.CENTER);

    }

    void initLayoutGuesses(int nGuesses, int guessLength){
        guessesView = new GuessesView(uiSettings,nGuesses,guessLength);
        this.setCenter(guessesView);
    }



    GuessesView getGuessesView() {
        return guessesView;
    }

    OverzichtView getOverzichtView() {
        return overzichtView;
    }

    MenuBarView getMenuBarView(){return menuBarView;}

    Label getMessageLabel(){return messageLabel;}

}

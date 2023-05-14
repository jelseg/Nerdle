package Nerdle.View.MainScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.MenuBarScreen.MenuBarView;
import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MainScreenView extends BorderPane {

    UISettings uiSettings;

    GuessesView guessesView;
    OverzichtView overzichtView;

    MenuBarView menuBarView;

    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        this.guessesView = new GuessesView(uiSettings, Difficulty.NORMAL.getComboLength(), Difficulty.NORMAL.getnTries());
        this.overzichtView = new OverzichtView(uiSettings);
        this.menuBarView = new MenuBarView(uiSettings);

    }

    private void layoutNodes() {


        setTop(menuBarView);
        setCenter(guessesView);
        setBottom(overzichtView);
        guessesView.setAlignment(Pos.CENTER);
        overzichtView.setAlignment(Pos.CENTER);

    }

    void initLayoutGuesses(int nGuesses, int guessLength) {
        guessesView = new GuessesView(uiSettings, nGuesses, guessLength);
        this.setCenter(guessesView);
    }


    GuessesView getGuessesView() {
        return guessesView;
    }

    OverzichtView getOverzichtView() {
        return overzichtView;
    }

    MenuBarView getMenuBarView() {
        return menuBarView;
    }

}

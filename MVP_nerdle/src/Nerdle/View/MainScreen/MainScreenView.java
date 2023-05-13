package Nerdle.View.MainScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.MenuBarScreen.MenuBarPresenter;
import Nerdle.View.MenuBarScreen.MenuBarView;
import Nerdle.View.UISettings;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class MainScreenView extends BorderPane {

    UISettings uiSettings;

    GuessesView guessesView;
    OverzichtView overzichtView;

    MenuBarView menuBarView;

    public MainScreenView(UISettings uiSettings,Difficulty difficulty) {
        this.uiSettings = uiSettings;
        initialiseNodes(difficulty);
        layoutNodes();
    }

    private void initialiseNodes(Difficulty difficulty) {

        this.guessesView =new GuessesView(uiSettings, difficulty.getnTries(), difficulty.getComboLength());
        this.overzichtView = new OverzichtView(uiSettings);
        this.menuBarView = new MenuBarView(uiSettings);


    }

    private void layoutNodes() {


        setTop(menuBarView);
        setCenter(guessesView);
        setBottom(overzichtView);

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

}

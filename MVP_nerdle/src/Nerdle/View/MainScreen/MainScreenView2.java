package Nerdle.View.MainScreen;

import Nerdle.View.UISettings;
import javafx.scene.layout.BorderPane;

public class MainScreenView2 extends BorderPane {

    UISettings uiSettings;

    GuessesView guessesView;
    OverzichtView overzichtView;




    public MainScreenView2(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

    }

    private void layoutNodes() {

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
}

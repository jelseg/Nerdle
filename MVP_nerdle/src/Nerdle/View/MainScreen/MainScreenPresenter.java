package Nerdle.View.MainScreen;

import Nerdle.Model.*;
import Nerdle.View.AboutScreen.*;
import Nerdle.View.FalseGuessScreen.FalseGuessScreenPresenter;
import Nerdle.View.FalseGuessScreen.FalseGuessScreenView;
import Nerdle.View.MenuBarScreen.MenuBarPresenter;
import Nerdle.View.RulesScreen.*;
import Nerdle.View.SettingsScreen.*;
import Nerdle.View.UISettings;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MainScreenPresenter {

    private Nerdle model;
    private MainScreenView view;
    private UISettings uiSettings;
    private MainScreenTransition mainScreenTransition;
    int column=0;
    int row=0;
    MenuBarPresenter menuBarPresenter;

    public MainScreenPresenter(Nerdle model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        column =0;
        row =0;
        initOverzicht();
        menuBarPresenter = new MenuBarPresenter(view.getMenuBarView(),uiSettings);

        updateView();
        EventHandlers();
    }

    private void updateView() {

        System.out.println(model.getCurrentGuess().getCombinationString());

        updateOverzicht();
        updateGuessesView();
     }

    private void EventHandlers() {

        addOverzichtEventHandlers();

    }


    private void addOverzichtEventHandlers(){

        //loop over characters in overzicht:
        for (int i = 0; i < model.getOverzicht().getPossibilities().length; i++){
            EquationCharacter eqc = model.getOverzicht().getPossibility(i);

            CharacterTile currentTile = view.getOverzichtView().get(i);

            currentTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    model.addToCurrentGuess(eqc.copyGray());

                    updateView();
                }
            });
        }

        //enter button:
        view.getOverzichtView().getEnterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!model.enterCurrentGuess()){
                    FalseGuessScreenView falseGuessScreenView = new FalseGuessScreenView();
                    FalseGuessScreenPresenter falseGuessScreenPresenter = new FalseGuessScreenPresenter(falseGuessScreenView, uiSettings);
                    Stage falseguesStage = new Stage();
                    falseguesStage.setTitle("False guess");
                    falseguesStage.initOwner(view.getScene().getWindow());
                    falseguesStage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(falseGuessScreenView);
                    falseguesStage.setScene(scene);
                    falseguesStage.setTitle(uiSettings.getApplicationName() + " - False guess");
                    falseguesStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                    falseguesStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                    if (Files.exists(uiSettings.getApplicationIconPath())) {
                        try {
                            falseguesStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    } else { // do nothing, if ApplicationIconImage is not available, program can continue
                    }
                    falseGuessScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 5);
                    falseGuessScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 5);
                    if (uiSettings.styleSheetAvailable()) {
                        falseguesStage.getScene().getStylesheets().removeAll();
                        try {
                            falseguesStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    }
                    falseguesStage.showAndWait();
                    if (uiSettings.styleSheetAvailable()) {
                        view.getScene().getStylesheets().removeAll();
                        try {
                            view.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    }
                    //return;
                }


                //model.enterCurrentGuess();
                updateView();
                row++;
                column=0;
                if(model.isFoundIt()){
                    mainScreenTransition=new MainScreenTransition(view);
                    mainScreenTransition.play();
                } else if (model.isOver()) {

                    //placeholder

                }
            }
        });

        view.getOverzichtView().getBackSpaceButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.deleteFromCurrentGuess();
                //--column;
                //guessesArray[column][row].setImage(new Image("images/darkgrey.png"));
                updateView();
            }
        });

    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }

    private void handleCloseEvent(Event event){
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You're closing the game.");
        stopWindow.setContentText("Are you sure?");
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("No");
        ButtonType yesButton = new ButtonType("Yes");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }


    private void initOverzicht(){
        for(EquationCharacter eqc: model.getOverzicht().getPossibilities()){
            if(eqc.getOperation() == EquationCharacter.Operation.NUMBER){
                view.getOverzichtView().addNumber(eqc);
            }
            else {
                view.getOverzichtView().addOperation(eqc);
            }
        }
    }

    private void updateOverzicht(){



        for (int i = 0; i < model.getOverzicht().getPossibilities().length; i++) {
            EquationCharacter eqc = model.getOverzicht().getPossibility(i);

            CharacterTile currentTile = view.getOverzichtView().get(i);

            currentTile.changeCharacter(eqc);
        }

    }

    private void updateGuessesView(){
        List<Combination> allGuesses = model.getGuesses();
        Combination currentguess = model.getCurrentGuess();

        GuessesView gv = view.getGuessesView();

        //update all done guesses
        // for future updates: performance
        for (int i =0; i < allGuesses.size(); i++){
            EquationCharacter[] guessCharacters = allGuesses.get(i).getCharacters();
            for (int j = 0; j < guessCharacters.length; j++){
                gv.getElement(i,j).changeCharacter(guessCharacters[j]);
            }
        }

        if (!model.isOver()) {
            int i = allGuesses.size();
            EquationCharacter[] curChars = currentguess.getCharacters();
            for (int j = 0; j < curChars.length; j++) {
                gv.getElement(i, j).changeCharacter(curChars[j]);
                //System.out.println(j + curChars[j].getOperation().toString());
            }
        }

    }
}

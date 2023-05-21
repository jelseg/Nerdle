package Nerdle.View.MainMenuScreen;

import Nerdle.Model.MVPModel;
import Nerdle.View.AboutScreen.AboutScreenPresenter;
import Nerdle.View.AboutScreen.AboutScreenView;
import Nerdle.View.HighscoresScreen.HighscoresScreenPresenter;
import Nerdle.View.HighscoresScreen.HighscoresScreenView;
import Nerdle.View.NewGameScreen.NewGameScreenPresenter;
import Nerdle.View.NewGameScreen.NewGameScreenView;
import Nerdle.View.RulesScreen.RulesScreenPresenter;
import Nerdle.View.RulesScreen.RulesScreenView;
import Nerdle.View.SettingsScreen.SettingsPresenter;
import Nerdle.View.SettingsScreen.SettingsView;
import Nerdle.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.nio.file.Files;

public class MainMenuScreenPresenter {

    private MVPModel model;
    private MainMenuScreenView view;
    private UISettings uiSettings;

    public MainMenuScreenPresenter(MVPModel model,MainMenuScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        EventHandlers();
    }


    private void EventHandlers() {

        view.getNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                NewGameScreenView newGameView = new NewGameScreenView(uiSettings);
                NewGameScreenPresenter newGameScreenPresenter = new NewGameScreenPresenter(newGameView,uiSettings,view.getScene());
                Stage newGameStage = new Stage();
                // set owner to window of this presenters view
                newGameStage.initOwner(view.getScene().getWindow());
                newGameStage.initModality(Modality.APPLICATION_MODAL);
                newGameStage.setTitle(uiSettings.getApplicationName() + " - New game");
                newGameStage.setScene(new Scene(newGameView));
                newGameStage.setX(view.getScene().getWindow().getWidth()-view.getScene().getWindow().getWidth()/5);
                newGameStage.setY(view.getScene().getWindow().getHeight()/2);
                if (uiSettings.styleSheetAvailable()) {
                    newGameStage.getScene().getStylesheets().removeAll();
                    try {
                        newGameStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                newGameStage.showAndWait();

            }
        });

        view.getAbout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(model, aboutScreenView, uiSettings);
                Stage aboutStage = new Stage();
                aboutStage.initOwner(view.getScene().getWindow());
                aboutStage.initModality(Modality.APPLICATION_MODAL);
                aboutStage.setTitle(uiSettings.getApplicationName() + " - About");
                aboutStage.setScene(new Scene(aboutScreenView));
                aboutStage.setX(view.getScene().getWindow().getWidth()-view.getScene().getWindow().getWidth()/3);
                aboutStage.setY(view.getScene().getWindow().getHeight()/3);
                if (uiSettings.styleSheetAvailable()) {
                    aboutStage.getScene().getStylesheets().removeAll();
                    try {
                        aboutStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                aboutStage.showAndWait();
            }
        });

        view.getRules().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RulesScreenView rulesScreenView = new RulesScreenView(uiSettings);
                RulesScreenPresenter rulesScreenPresenter = new RulesScreenPresenter(model, rulesScreenView, uiSettings);
                Stage rulesScreenStage = new Stage();
                rulesScreenStage.initOwner(view.getScene().getWindow());
                rulesScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(rulesScreenView);
                rulesScreenStage.setScene(scene);
                rulesScreenStage.setTitle(uiSettings.getApplicationName() + " - Rules");
                rulesScreenStage.setX(view.getScene().getWindow().getX() );
                rulesScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 40);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        rulesScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                rulesScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 2);
                rulesScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 2);
                if (uiSettings.styleSheetAvailable()) {
                    rulesScreenView.getScene().getStylesheets().removeAll();
                    try {
                        rulesScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                rulesScreenStage.showAndWait();
            }
        });

        view.getSettings().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SettingsView settingsView = new SettingsView(uiSettings);
                SettingsPresenter presenter = new SettingsPresenter(model, settingsView, uiSettings);
                Stage settingsStage = new Stage();
                settingsStage.setTitle("Settings");
                settingsStage.initOwner(view.getScene().getWindow());
                settingsStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(settingsView);
                settingsStage.setScene(scene);
                settingsStage.setTitle(uiSettings.getApplicationName() + " - Settings");
                settingsStage.setX(view.getScene().getWindow().getX() );
                settingsStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 40);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        settingsStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                settingsView.getScene().getWindow().setHeight(uiSettings.getResY()/2);
                settingsView.getScene().getWindow().setWidth(uiSettings.getResX()/1.8);
                if (uiSettings.styleSheetAvailable()) {
                    settingsStage.getScene().getStylesheets().removeAll();
                    try {
                        settingsStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                settingsStage.showAndWait();
                if (uiSettings.styleSheetAvailable()) {
                    view.getScene().getStylesheets().removeAll();
                    try {
                        view.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
            }
        });

        view.getHighScores().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HighscoresScreenView highscoresScreenView=new HighscoresScreenView(uiSettings);
                HighscoresScreenPresenter highscoresScreenPresenter=new HighscoresScreenPresenter(highscoresScreenView,uiSettings);
                Stage highscoreStage=new Stage();
                highscoreStage.setTitle("Highscores");
                highscoreStage.initOwner(view.getScene().getWindow());
                highscoreStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(highscoresScreenView);
                highscoreStage.setScene(scene);
                highscoreStage.setTitle(uiSettings.getApplicationName() + " - Highscores");
                highscoreStage.setX(view.getScene().getWindow().getWidth()-view.getScene().getWindow().getWidth()/3);
                highscoreStage.setY( view.getScene().getWindow().getHeight()/3);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        highscoreStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                highscoresScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 2);
                highscoresScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 3);
                if (uiSettings.styleSheetAvailable()) {
                    highscoreStage.getScene().getStylesheets().removeAll();
                    try {
                        highscoreStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                highscoreStage.showAndWait();
                if (uiSettings.styleSheetAvailable()) {
                    view.getScene().getStylesheets().removeAll();
                    try {
                        view.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }

            }
        });


        view.getExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCloseEvent(event);
            }
        });
    }

    private void updateView() {
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


}

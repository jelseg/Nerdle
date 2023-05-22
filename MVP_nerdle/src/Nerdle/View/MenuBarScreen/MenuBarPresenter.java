package Nerdle.View.MenuBarScreen;

import Nerdle.Model.MVPModel;
import Nerdle.View.AboutScreen.AboutScreenPresenter;
import Nerdle.View.AboutScreen.AboutScreenView;
import Nerdle.View.HighscoresScreen.HighscoresScreenPresenter;
import Nerdle.View.HighscoresScreen.HighscoresScreenView;
import Nerdle.View.MainMenuScreen.MainMenuScreenPresenter;
import Nerdle.View.MainMenuScreen.MainMenuScreenView;
import Nerdle.View.MainScreen.MainScreenPresenter;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MenuBarPresenter {

    MenuBarView view;
    UISettings uiSettings;

    public MenuBarPresenter(MenuBarView menuBarView, UISettings uiSettings){
        this.view = menuBarView;
        this.uiSettings = uiSettings;

        //updateView();
        EventHandlers();
    }

    private void EventHandlers(){
        view.getSettingsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsView settingsView = new SettingsView(uiSettings);
                SettingsPresenter presenter = new SettingsPresenter(new MVPModel(), settingsView, uiSettings);
                Stage settingsStage = new Stage();
                settingsStage.setTitle("Settings");
                settingsStage.initOwner(view.getScene().getWindow());
                settingsStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(settingsView);
                settingsStage.setScene(scene);
                settingsStage.setTitle(uiSettings.getApplicationName() + " - Settings");
                settingsStage.setX(view.getScene().getWindow().getWidth()-view.getScene().getWindow().getWidth()/3);
                settingsStage.setY( view.getScene().getWindow().getHeight()/3);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        settingsStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                settingsView.getScene().getWindow().setHeight(uiSettings.getResY()/5);
                settingsView.getScene().getWindow().setWidth(uiSettings.getResX()/2);
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

        view.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCloseEvent(event);
            }
        });
        view.getAboutItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(new MVPModel(), aboutScreenView, uiSettings);
                Stage aboutScreenStage = new Stage();
                aboutScreenStage.initOwner(view.getScene().getWindow());
                aboutScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(aboutScreenView);
                aboutScreenStage.setScene(scene);
                aboutScreenStage.setTitle(uiSettings.getApplicationName() + " - About");
                aboutScreenStage.setX(view.getScene().getWindow().getX() );
                aboutScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 40);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        aboutScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                if (uiSettings.styleSheetAvailable()) {
                    aboutScreenView.getScene().getStylesheets().removeAll();
                    try {
                        aboutScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                aboutScreenStage.showAndWait();
            }});
        view.getRulesItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RulesScreenView rulesScreenView = new RulesScreenView(uiSettings);
                RulesScreenPresenter rulesScreenPresenter = new RulesScreenPresenter(new MVPModel(), rulesScreenView, uiSettings);
                Stage rulesScreenStage = new Stage();
                rulesScreenStage.initOwner(view.getScene().getWindow());
                rulesScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(rulesScreenView);
                rulesScreenStage.setScene(scene);
                rulesScreenStage.setTitle(uiSettings.getApplicationName()+ " - Rules");
                rulesScreenStage.setX(view.getScene().getWindow().getX() );
                rulesScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 40);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        rulesScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                rulesScreenView.getScene().getWindow().setHeight(uiSettings.getResY()/2);
                rulesScreenView.getScene().getWindow().setWidth(uiSettings.getResX()/3);
                if (uiSettings.styleSheetAvailable()){
                    rulesScreenView.getScene().getStylesheets().removeAll();
                    try {
                        rulesScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                rulesScreenStage.showAndWait();
            }});

        view.getNewGameLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NewGameScreenView newGameView = new NewGameScreenView(uiSettings);
                NewGameScreenPresenter newGameScreenPresenter = new NewGameScreenPresenter(newGameView,uiSettings,view.getScene());
                Stage newGameStage = new Stage();
                // set owner to window of this presenters view
                newGameStage.initOwner(view.getScene().getWindow());
                newGameStage.initModality(Modality.APPLICATION_MODAL);
                newGameStage.setTitle(uiSettings.getApplicationName() + " - New game");
                newGameStage.setScene(new Scene(newGameView));
                newGameStage.setX(view.getScene().getWindow().getX()+80);
                newGameStage.setY(view.getScene().getWindow().getY()+50);
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

        view.getHighscoresMI().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HighscoresScreenView highscoresScreenView=new HighscoresScreenView(uiSettings);
                HighscoresScreenPresenter highscoresScreenPresenter=new HighscoresScreenPresenter(highscoresScreenView,uiSettings);
                Stage highscoreStage=new Stage();
                highscoreStage.setTitle(uiSettings.getApplicationName()+" - Highscores");
                highscoreStage.initOwner(view.getScene().getWindow());
                highscoreStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(highscoresScreenView);
                highscoreStage.setScene(scene);
                highscoreStage.setX(view.getScene().getWindow().getX() + 40);
                highscoreStage.setY(view.getScene().getWindow().getY() + 10);
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

        view.getMainMenuLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenuScreenView mmSv=new MainMenuScreenView();
                view.getScene().setRoot(mmSv);
                MainMenuScreenPresenter mmSp=new MainMenuScreenPresenter(new MVPModel(),mmSv,uiSettings);
                mmSv.getScene().getWindow().setHeight(uiSettings.getResY() / 2);
                mmSv.getScene().getWindow().setWidth(uiSettings.getResX() / 2);
                try {
                    mmSv.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                mmSv.getScene().getWindow().centerOnScreen();
                mmSp.windowsHandler();
            }
        });



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

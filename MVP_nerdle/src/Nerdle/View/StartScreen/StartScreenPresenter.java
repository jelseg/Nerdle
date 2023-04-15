package Nerdle.View.StartScreen;

import Nerdle.Model.*;
import Nerdle.View.*;
import Nerdle.View.MainMenuScreen.MainMenuScreenPresenter;
import Nerdle.View.MainMenuScreen.MainMenuScreenView;
import Nerdle.View.MainScreen.MainScreenPresenter;
import Nerdle.View.MainScreen.MainScreenView;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import java.net.MalformedURLException;

public class StartScreenPresenter {

    private MVPModel model;
    private StartScreenView view;
    private UISettings uiSettings;

    public StartScreenPresenter(MVPModel model, StartScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        addEventHandlers();
        updateView();

    }

    private void updateView() {
    }

    private void addEventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MainMenuScreenView mmSv=new MainMenuScreenView();
                view.getScene().setRoot(mmSv);
                MainMenuScreenPresenter mmSp=new MainMenuScreenPresenter(model, mmSv,uiSettings);
                try {
                    mmSv.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
//                mmSv.getScene().getWindow().sizeToScene();
//                mmSv.getScene().getWindow().setX(uiSettings.getResX()/20);
//                mmSv.getScene().getWindow().setY(uiSettings.getResY()/20);
//                mmSv.getScene().getWindow().setHeight(5 * uiSettings.getResY()/10);
//                mmSv.getScene().getWindow().setWidth(5 * uiSettings.getResX()/10);
//                mmSp.windowsHandler();
                updateView();
                mmSp.windowsHandler();
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
             @Override
             public void handle(WindowEvent event) {
                 final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                 stopWindow.setHeaderText("You can not yet close the application.");
                 stopWindow.setContentText("Try again after the program has started");
                 stopWindow.showAndWait();
                 event.consume(); } });
    }
}

package MVPGlobal.View.StartScreen;

import MVPGlobal.Model.*;
import MVPGlobal.View.*;
import MVPGlobal.View.MainScreen.MainScreenPresenter;
import MVPGlobal.View.MainScreen.MainScreenView;
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
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainScreenView msView = new MainScreenView(uiSettings);
                MainScreenPresenter msPresenter = new MainScreenPresenter(model, msView, uiSettings);
                view.getScene().setRoot(msView);
                try {
                    msView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                msView.getScene().getWindow().sizeToScene();
                msView.getScene().getWindow().setX(uiSettings.getResX()/20);
                msView.getScene().getWindow().setY(uiSettings.getResY()/20);
                msView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
                msView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
                msPresenter.windowsHandler();
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

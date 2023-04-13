package Nerdle.View.MainMenuScreen;

import Nerdle.Model.MVPModel;
import Nerdle.View.AboutScreen.AboutScreenPresenter;
import Nerdle.View.AboutScreen.AboutScreenView;
import Nerdle.View.MainScreen.MainScreenPresenter;
import Nerdle.View.MainScreen.MainScreenView;
import Nerdle.View.NewUserScreen.NewUserScreenPresenter;
import Nerdle.View.NewUserScreen.NewUserScreenView;
import Nerdle.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;

public class MainMenuScreenPresenter {

    private MVPModel model;
    private MainMenuScreenView view;
    private UISettings uiSettings;

    public MainMenuScreenPresenter(MVPModel model, MainMenuScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        EventHandlers();
    }



    private void EventHandlers() {

        view.getNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NewUserScreenView newUserScreenView=new NewUserScreenView();
                NewUserScreenPresenter newUserScreenPresenter=new NewUserScreenPresenter(model,newUserScreenView,uiSettings);
                Stage newUserStage = new Stage();
                newUserStage.initOwner(view.getScene().getWindow());
                newUserStage.initModality(Modality.APPLICATION_MODAL);
                newUserStage.setScene(new Scene(newUserScreenView));
                newUserStage.setX(view.getScene().getWindow().getX() + 100);
                newUserStage.setY(view.getScene().getWindow().getY() + 100);
                newUserStage.showAndWait();
            }
        });

//        view.getNewGame().setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                MainScreenView mainScreenView=new MainScreenView(uiSettings);
//                MainScreenPresenter mainScreenPresenter=new MainScreenPresenter(model,mainScreenView,uiSettings);
//                view.getScene().setRoot(mainScreenView);
//                try {
//                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
//                } catch (MalformedURLException ex) {
//                    // // do nothing, if toURL-conversion fails, program can continue
//                }
//                mainScreenView.getScene().getWindow().sizeToScene();
//                updateView();
//
////                msView.getScene().getWindow().setX(uiSettings.getResX() / 20);
////                msView.getScene().getWindow().setY(uiSettings.getResY() / 20);
////                msView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 10);
////                msView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 10);
//                mainScreenPresenter.windowsHandler();
//            }
//        });

        view.getAbout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("klik");
//                AboutScreenView aboutScreenView=new AboutScreenView(uiSettings);
//                AboutScreenPresenter aboutScreenPresenter=new AboutScreenPresenter(model, aboutScreenView, uiSettings);
//                Stage aboutStage =new Stage();
//                aboutStage.initOwner(view.getScene().getWindow());
//                aboutStage.initModality(Modality.APPLICATION_MODAL);
//                aboutStage.setScene(new Scene(aboutScreenView));
//                aboutStage.setX(view.getScene().getWindow().getX() + 100);
//                aboutStage.setY(view.getScene().getWindow().getY() + 100);
//                aboutStage.showAndWait();
            }
        });

    }
    private void updateView() {
    }
}

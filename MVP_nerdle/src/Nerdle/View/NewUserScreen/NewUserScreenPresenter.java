package Nerdle.View.NewUserScreen;

import Nerdle.Model.Difficulty;
import Nerdle.Model.MVPModel;
import Nerdle.Model.Nerdle;
import Nerdle.View.MainScreen.MainScreenPresenter;
import Nerdle.View.MainScreen.MainScreenView;
import Nerdle.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.net.MalformedURLException;

public class NewUserScreenPresenter{

    private MVPModel model;
    private NewUserScreenView view;
    private UISettings uiSettings;

    public NewUserScreenPresenter(MVPModel model,NewUserScreenView view,UISettings uiSettings){
        this.model=model;
        this.view=view;
        this.uiSettings=uiSettings;
        addEventHandlers();
        updateview();
    }

    private void addEventHandlers(){
        view.getBtnUsername().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainScreenView mainScreenView=new MainScreenView(uiSettings, Difficulty.NORMAL);
                MainScreenPresenter mainScreenPresenter=new MainScreenPresenter(new Nerdle(),mainScreenView,uiSettings);
                view.getScene().setRoot(mainScreenView);
                try {
                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    //do nothing, if toURL-conversion fails, program can continue
                }
                mainScreenView.getScene().getWindow().sizeToScene();
                mainScreenPresenter.windowsHandler();
            }
        });
    }
    private void updateview(){

    }

}

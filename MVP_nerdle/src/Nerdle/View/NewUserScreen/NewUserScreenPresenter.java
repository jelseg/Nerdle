package Nerdle.View.NewUserScreen;

import Nerdle.Model.MVPModel;
import Nerdle.View.MainScreen.MainScreenPresenter;
import Nerdle.View.MainScreen.MainScreenView;
import Nerdle.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

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
                MainScreenView mainScreenView=new MainScreenView(uiSettings);
                MainScreenPresenter mainScreenPresenter=new MainScreenPresenter(model,mainScreenView,uiSettings);
                view.getScene().setRoot(mainScreenView);
                mainScreenView.getScene().getWindow().sizeToScene();
            }
        });
    }
    private void updateview(){

    }

}

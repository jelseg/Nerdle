package Nerdle.View.FalseGuessScreen;

import Nerdle.View.UISettings;
import javafx.event.EventHandler;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class FalseGuessScreenPresenter {

    private FalseGuessScreenView view;
    private UISettings uiSettings;

    public FalseGuessScreenPresenter(FalseGuessScreenView view, UISettings uiSettings){
        this.view=view;
        this.uiSettings=uiSettings;
        updateView();
        EventHandlers();
    }
    private void updateView(){

    }
    private void EventHandlers(){
        view.getOkButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) { handleCloseEvent(mouseEvent);
            }
        });
    }
    private void handleCloseEvent(Event event){
        view.getScene().getWindow().hide();
    }
}


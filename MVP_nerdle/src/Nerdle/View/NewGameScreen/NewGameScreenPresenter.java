package Nerdle.View.NewGameScreen;


import Nerdle.Model.Difficulty;
import Nerdle.Model.Nerdle;
import Nerdle.Model.NerdleException;
import Nerdle.Model.User;
import Nerdle.View.MainScreen.MainScreenPresenter;
import Nerdle.View.MainScreen.MainScreenView;
import Nerdle.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.net.MalformedURLException;
import java.util.List;

public class NewGameScreenPresenter {

    private List<User> usersModel;
    private NewGameScreenView view;
    private UISettings uiSettings;

    private Scene mainScene;

    public NewGameScreenPresenter(NewGameScreenView view, UISettings uiSettings,Scene mainScene){
        try {
            usersModel = User.getAllUsers();
        }catch (NerdleException n){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to load");
            alert.setContentText(n.getMessage());
            alert.showAndWait();
        }
        this.view = view;
        this.uiSettings=uiSettings;
        this.mainScene = mainScene;
        addEventHandlers();
        updateview();
    }

    private void updateview(){

        String[] userNames = new String[usersModel.size()];
        for(int i = 0; i < usersModel.size(); i++){
            userNames[i] = usersModel.get(i).getName();
        }

        view.addUsers(userNames);
    }

    private void startNewGame(String username){

        User userChoosen = new User(username);
        Difficulty difficulty = view.getDifficultyComboBox().getValue();
        Nerdle nerdleModel = new Nerdle(userChoosen, difficulty);

        MainScreenView mainScreenView=new MainScreenView(uiSettings,difficulty);
        MainScreenPresenter mainScreenPresenter=new MainScreenPresenter(nerdleModel,mainScreenView,uiSettings);

        view.getScene().getWindow().hide();
        mainScene.setRoot(mainScreenView);
        try {
            mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
        } catch (MalformedURLException ex) {
            //do nothing, if toURL-conversion fails, program can continue
        }
        mainScreenView.getScene().getWindow().sizeToScene();
        mainScreenView.getScene().getWindow().centerOnScreen();
        mainScreenPresenter.windowsHandler();



    }

    private void addEventHandlers(){

        view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String userName = view.getUserBox().getValue();
                System.out.println(userName);

                if(userName != null && !userName.trim().isEmpty()){
                    startNewGame(userName);
                }

            }
        });

        view.getCreateButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String userName = view.getUsername().getText();
                System.out.println(userName);

                if(userName != null && !userName.trim().isEmpty()){
                    startNewGame(userName);
                }

            }
        });


    }

}

package Nerdle.View.MainScreen;

import Nerdle.View.GameScreen.AnswersScreenView;
import Nerdle.View.GameScreen.ButtonsScreenView;
import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem rulesMI;
    private MenuItem savegameMI;
    private MenuItem newgameMI;
    private MenuItem highscoresMI;
    private UISettings uiSettings;


    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.saveMI = new MenuItem("Save");
        this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.rulesMI = new MenuItem("Rules");
        this.savegameMI=new MenuItem("Save");
        this.newgameMI=new MenuItem("New game");
        this.highscoresMI=new MenuItem("Highscores");
            }




    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuNewGame=new Menu("New game",null);
        Menu menuSavegame= new Menu("Save game",null);
        Menu menuHighscores=new Menu("Highscores",null);
        Menu menuHelp = new Menu("Help",null, aboutMI, rulesMI);
        MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuSavegame,menuHighscores,menuHelp);
        setTop(menuBar);
        AnswersScreenView answersScreenView =new AnswersScreenView(uiSettings);
        setCenter(answersScreenView);
        setMargin(answersScreenView,new Insets(10));
        ButtonsScreenView buttonsScreenView=new ButtonsScreenView();
        setBottom(buttonsScreenView);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getRulesItem() {return rulesMI;}

    public MenuItem getNewgameMI() {return newgameMI;}

    public MenuItem getSavegameMI() {return savegameMI;}

    public MenuItem getHighscoresMI() {return highscoresMI;}

    public UISettings getUiSettings() {return uiSettings;}

}

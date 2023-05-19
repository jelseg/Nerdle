package Nerdle.View.MenuBarScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.MainScreen.GuessesView;
import Nerdle.View.MainScreen.OverzichtView;
import Nerdle.View.UISettings;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class MenuBarView extends MenuBar {


    //elements top menu bar
    private MenuItem exitMI;
    //private MenuItem saveMI;
    //private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem rulesMI;
    //private MenuItem savegameMI;
    //private MenuItem newgameMI;
    private Label newGameLabel;
    private MenuItem highscoresMI;
    private Label mainMenuLabel;
    //private MenuItem mainMenuMI;

    private UISettings uiSettings;


    public MenuBarView(UISettings uiSettings){
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        //menu bar
        this.exitMI = new MenuItem("Exit");
        //this.saveMI = new MenuItem("Save");
        //this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.rulesMI = new MenuItem("Rules");
        //this.savegameMI=new MenuItem("Save");
        //this.newgameMI=new MenuItem("New game");
        this.newGameLabel = new Label("New Game");
        this.highscoresMI=new MenuItem("Highscores");
        //this.mainMenuMI = new MenuItem("Main Menu");
        this.mainMenuLabel = new Label("Main Menu");
    }

    private void layoutNodes() {

        //menu bar
        Menu menuFile = new Menu("File",null, new SeparatorMenuItem(), settingsMI,highscoresMI, new SeparatorMenuItem(),exitMI);
        //Menu menuNewGame=new Menu("New game",null,newgameMI);
        Menu menuNewGame = new Menu("",newGameLabel);
        //Menu menuSavegame= new Menu("Save game",null); //will be done automaticaly
        //Menu menuHighscores=new Menu("Highscores",null,highscoresMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, rulesMI);
        Menu menuMain = new Menu("",mainMenuLabel);
        //MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuHighscores,menuHelp);
        //getChildren().addAll(menuBar);
        this.getMenus().addAll(menuFile,menuNewGame,menuMain,menuHelp);

    }


    //getters menu bar items:
    MenuItem getExitItem() {return exitMI;}

    //MenuItem getSaveItem() {return saveMI;}

    //MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getRulesItem() {return rulesMI;}

    //MenuItem getNewgameMI() {return newgameMI;}

    //public MenuItem getSavegameMI() {return savegameMI;}

    MenuItem getHighscoresMI() {return highscoresMI;}
    //MenuItem getMainMenuMI() {return mainMenuMI;}
    Label getMainMenuLabel(){return mainMenuLabel;}

    Label getNewGameLabel(){return newGameLabel;}
}

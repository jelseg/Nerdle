package Nerdle.View.MainScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.UISettings;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class MainScreenView extends BorderPane {

    UISettings uiSettings;

    GuessesView guessesView;
    OverzichtView overzichtView;


    //elements top menu bar
    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem rulesMI;
    private MenuItem savegameMI;
    private MenuItem newgameMI;
    private MenuItem highscoresMI;




    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        //menu bar
        this.exitMI = new MenuItem("Exit");
        this.saveMI = new MenuItem("Save");
        this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.rulesMI = new MenuItem("Rules");
        this.savegameMI=new MenuItem("Save");
        this.newgameMI=new MenuItem("New game");
        this.highscoresMI=new MenuItem("Highscores");

        this.guessesView =new GuessesView(uiSettings, Difficulty.NORMAL.getComboLength(), Difficulty.NORMAL.getnTries());
        this.overzichtView = new OverzichtView(uiSettings);


    }

    private void layoutNodes() {

        //menu bar
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuNewGame=new Menu("New game",null);
        Menu menuSavegame= new Menu("Save game",null);
        Menu menuHighscores=new Menu("Highscores",null);
        Menu menuHelp = new Menu("Help",null, aboutMI, rulesMI);
        MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuSavegame,menuHighscores,menuHelp);
        setTop(menuBar);
        setCenter(guessesView);
        setBottom(overzichtView);

    }

    void initLayoutGuesses(int nGuesses, int guessLength){
        guessesView = new GuessesView(uiSettings,nGuesses,guessLength);
        this.setCenter(guessesView);
    }



    GuessesView getGuessesView() {
        return guessesView;
    }

    OverzichtView getOverzichtView() {
        return overzichtView;
    }



    //getters menu bar items:
    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getRulesItem() {return rulesMI;}

    public MenuItem getNewgameMI() {return newgameMI;}

    public MenuItem getSavegameMI() {return savegameMI;}

    public MenuItem getHighscoresMI() {return highscoresMI;}
}

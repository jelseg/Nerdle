package MVPGlobal.View.MainScreen;

import MVPGlobal.View.UISettings;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem rulesMI;
    private MenuItem infoMI;
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
        this.rulesMI = new MenuItem("Rules");
        this.infoMI = new MenuItem("Info");
        this.savegameMI=new MenuItem("Save");
        this.newgameMI=new MenuItem("New game");
        this.highscoresMI=new MenuItem("Highscores");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuNewGame=new Menu("New game",null);
        Menu menuSavegame= new Menu("Save game",null);
        Menu menuHighscores=new Menu("Highscores",null);
        Menu menuHelp = new Menu("Help",null, rulesMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuSavegame,menuHighscores,menuHelp);
        setTop(menuBar);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return rulesMI;}

    MenuItem getInfoItem() {return infoMI;}

    public MenuItem getNewgameMI() {return newgameMI;}

    public MenuItem getSavegameMI() {return savegameMI;}

    public MenuItem getHighscoresMI() {return highscoresMI;}

    public UISettings getUiSettings() {return uiSettings;}
}

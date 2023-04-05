package Nerdle.View.MainScreen;

import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

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

    //Buttons and blocks
    private List<ImageView> imageViews;
    private Image blanco;
    private Image lightgray0;
    private ImageView iv0;
    private Button button0;
    private Image lightgray1;
    private ImageView iv1;
    private Button button1;
    private Image lightgray2;
    private ImageView iv2;
    private Button button2;
    private Image lightgray3;
    private ImageView iv3;
    private Button button3;

    private Image lightgray4;
    private ImageView iv4;
    private Button button4;

    private Image lightgray5;
    private ImageView iv5;
    private Button button5;

    private Image lightgray6;
    private ImageView iv6;
    private Button button6;

    private Image lightgray7;
    private ImageView iv7;
    private Button button7;

    private Image lightgray8;
    private ImageView iv8;
    private Button button8;

    private Image lightgray9;
    private ImageView iv9;
    private Button button9;

    private Image lightgrayplus;
    private ImageView ivplus;
    private Button buttonplus;

    private Image lightgrayminus;
    private ImageView ivminus;
    private Button buttonminus;

    private Image lightgraymultiply;
    private ImageView ivmultiply;
    private Button buttonmultiply;

    private Image lightgraydivide;
    private ImageView ivdivide;
    private Button buttondivide;

    private Image lightgrayequals;
    private ImageView ivequals;
    private Button buttonequals;

    private Image delete;
    private ImageView ivdelete;
    private Button buttondelete;

    private Image enter;
    private ImageView iventer;
    private Button buttonenter;

    private Button testbutton;

    private GridPane gridPane;
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

        //Gridpane inside Borderpane
        GridPane gridPane=new GridPane();
        setCenter(gridPane);

        //Buttons and blocks

        blanco = new Image("images/darkgrey.png");
        imageViews = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 8; column++) {
                ImageView iv = new ImageView(blanco);
                gridPane.add(iv, column, row);
                imageViews.add(iv);
            }
        }


                lightgray0 = new Image("images/buttons/lightgray0.png");
                iv0 = new ImageView(lightgray0);
                button0 = new Button();
                button0.setGraphic(iv0);
                gridPane.add(button0, 0, 6);

                lightgray1 = new Image("images/buttons/lightgray1.png");
                iv1 = new ImageView(lightgray1);
                button1 = new Button();
                button1.setGraphic(iv1);
                gridPane.add(button1, 1, 6);

                lightgray2 = new Image("images/buttons/lightgray2.png");
                iv2 = new ImageView(lightgray2);
                button2 = new Button();
                button2.setGraphic(iv2);
                gridPane.add(button2, 2, 6);

                lightgray3 = new Image("images/buttons/lightgray3.png");
                iv3 = new ImageView(lightgray3);
                button3 = new Button();
                button3.setGraphic(iv3);
                gridPane.add(button3, 3, 6);

                lightgray4 = new Image("images/buttons/lightgray4.png");
                iv4 = new ImageView(lightgray4);
                button4 = new Button();
                button4.setGraphic(iv4);
                gridPane.add(button4, 4, 6);

                lightgray5 = new Image("images/buttons/lightgray5.png");
                iv5 = new ImageView(lightgray5);
                button5 = new Button();
                button5.setGraphic(iv5);
                gridPane.add(button5, 5, 6);

                lightgray6 = new Image("images/buttons/lightgray6.png");
                iv6 = new ImageView(lightgray6);
                button6 = new Button();
                button6.setGraphic(iv6);
                gridPane.add(button6, 6, 6);

                lightgray7 = new Image("images/buttons/lightgray7.png");
                iv7 = new ImageView(lightgray7);
                button7 = new Button();
                button7.setGraphic(iv7);
                gridPane.add(button7, 7, 6);

                lightgray8 = new Image("images/buttons/lightgray8.png");
                iv8 = new ImageView(lightgray8);
                button8 = new Button();
                button8.setGraphic(iv8);
                gridPane.add(button8, 8, 6);

                lightgray9 = new Image("images/buttons/lightgray9.png");
                iv9 = new ImageView(lightgray9);
                button9 = new Button();
                button9.setGraphic(iv9);
                gridPane.add(button9, 9, 6);

                lightgrayplus = new Image("images/buttons/lightgray_plus.png");
                ivplus = new ImageView(lightgrayplus);
                buttonplus = new Button();
                buttonplus.setGraphic(ivplus);
                gridPane.add(buttonplus, 0, 7);

                lightgrayminus = new Image("images/buttons/lightgray_minus.png");
                ivminus = new ImageView(lightgrayminus);
                buttonminus = new Button();
                buttonminus.setGraphic(ivminus);
                gridPane.add(buttonminus, 1, 7);

                lightgraymultiply = new Image("images/buttons/lightgray_multiply.png");
                ivmultiply = new ImageView(lightgraymultiply);
                buttonmultiply = new Button();
                buttonmultiply.setGraphic(ivmultiply);
                gridPane.add(buttonmultiply, 2, 7);

                lightgraydivide = new Image("images/buttons/lightgray_divide.png");
                ivdivide = new ImageView(lightgraydivide);
                buttondivide = new Button();
                buttondivide.setGraphic(ivdivide);
                gridPane.add(buttondivide, 3, 7);

                lightgrayequals = new Image("images/buttons/lightgray_equals.png");
                ivequals = new ImageView(lightgrayequals);
                buttonequals = new Button();
                buttonequals.setGraphic(ivequals);
                gridPane.add(buttonequals, 4, 7);

                delete = new Image("images/buttons/delete.png");
                ivdelete = new ImageView(delete);
                buttondelete = new Button();
                buttondelete.setGraphic(ivdelete);
                gridPane.add(buttondelete, 5, 7);

                enter = new Image("images/buttons/enter.png");
                iventer = new ImageView(enter);
                buttonenter = new Button();
                buttonenter.setGraphic(iventer);
                buttonenter.setId("buttonenter");
                gridPane.add(iventer, 6, 7);

                testbutton=new Button("TEST");
                testbutton.setId("testbutton");
                gridPane.add(testbutton,0,8);
            }




    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuNewGame=new Menu("New game",null);
        Menu menuSavegame= new Menu("Save game",null);
        Menu menuHighscores=new Menu("Highscores",null);
        Menu menuHelp = new Menu("Help",null, aboutMI, rulesMI);
        MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuSavegame,menuHighscores,menuHelp);
        setTop(menuBar);

    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return rulesMI;}

    public MenuItem getNewgameMI() {return newgameMI;}

    public MenuItem getSavegameMI() {return savegameMI;}

    public MenuItem getHighscoresMI() {return highscoresMI;}

    public UISettings getUiSettings() {return uiSettings;}
}

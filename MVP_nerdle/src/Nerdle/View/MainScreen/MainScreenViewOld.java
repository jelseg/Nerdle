package Nerdle.View.MainScreen;

import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MainScreenViewOld extends BorderPane  {

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

    //game overview
    private List<ImageView> imageViewList;
    private Image blanco;

    //game controls
    private Image lightgray0;
    private ImageView iv0;

    private Image lightgray1;
    private ImageView iv1;

    private Image lightgray2;
    private ImageView iv2;

    private Image lightgray3;
    private ImageView iv3;

    private Image lightgray4;
    private ImageView iv4;

    private Image lightgray5;
    private ImageView iv5;

    private Image lightgray6;
    private ImageView iv6;

    private Image lightgray7;
    private ImageView iv7;

    private Image lightgray8;
    private ImageView iv8;

    private Image lightgray9;
    private ImageView iv9;

    private Image lightgrayplus;
    private ImageView ivplus;

    private Image lightgrayminus;
    private ImageView ivminus;

    private Image lightgraymultiply;
    private ImageView ivmultiply;

    private Image lightgraydivide;
    private ImageView ivdivide;

    private Image lightgrayequals;
    private ImageView ivequals;

    private Image delete;
    private ImageView ivdelete;

    private Image enter;
    private ImageView iventer;

    GridPane gameOverview=new GridPane();
    GridPane gameControls=new GridPane();
    GridPane gpNumbers=new GridPane();
    GridPane gpButtons=new GridPane();


    public MainScreenViewOld(UISettings uiSettings) {
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

        //game overview
        blanco = new Image("images/darkgrey.png");
        imageViewList = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 8; column++) {
                ImageView ivBlanco = new ImageView(blanco);
                gameOverview.add(ivBlanco, column, row);
                imageViewList.add(ivBlanco);
            }
        }

        //game controls
        lightgray0=new Image("images/buttons/lightgray0.png");
        iv0=new ImageView(lightgray0);
        gpNumbers.add(iv0,0,0);

        lightgray1=new Image("images/buttons/lightgray1.png");
        iv1=new ImageView(lightgray1);
        gpNumbers.add(iv1,1,0);

        lightgray2=new Image("images/buttons/lightgray2.png");
        iv2=new ImageView(lightgray2);
        gpNumbers.add(iv2,2,0);

        lightgray3=new Image("images/buttons/lightgray3.png");
        iv3=new ImageView(lightgray3);
        gpNumbers.add(iv3,3,0);

        lightgray4=new Image("images/buttons/lightgray4.png");
        iv4=new ImageView(lightgray4);
        gpNumbers.add(iv4,4,0);

        lightgray5=new Image("images/buttons/lightgray5.png");
        iv5=new ImageView(lightgray5);
        gpNumbers.add(iv5,5,0);

        lightgray6=new Image("images/buttons/lightgray6.png");
        iv6=new ImageView(lightgray6);
        gpNumbers.add(iv6,6,0);

        lightgray7=new Image("images/buttons/lightgray7.png");
        iv7=new ImageView(lightgray7);
        gpNumbers.add(iv7,7,0);

        lightgray8=new Image("images/buttons/lightgray8.png");
        iv8=new ImageView(lightgray8);
        gpNumbers.add(iv8,8,0);

        lightgray9=new Image("images/buttons/lightgray9.png");
        iv9=new ImageView(lightgray9);
        gpNumbers.add(iv9,9,0);

        lightgrayplus = new Image("images/buttons/lightgray_plus.png");
        ivplus = new ImageView(lightgrayplus);
        gpButtons.add(ivplus, 0, 1);

        lightgrayminus = new Image("images/buttons/lightgray_minus.png");
        ivminus = new ImageView(lightgrayminus);
        gpButtons.add(ivminus, 1,1 );

        lightgraymultiply = new Image("images/buttons/lightgray_multiply.png");
        ivmultiply = new ImageView(lightgraymultiply);
        gpButtons.add(ivmultiply, 2, 1);

        lightgraydivide = new Image("images/buttons/lightgray_divide.png");
        ivdivide = new ImageView(lightgraydivide);
        gpButtons.add(ivdivide, 3, 1);

        lightgrayequals = new Image("images/buttons/lightgray_equals.png");
        ivequals = new ImageView(lightgrayequals);
        gpButtons.add(ivequals, 4, 1);

        delete = new Image("images/buttons/delete.png");
        ivdelete = new ImageView(delete);
        gpButtons.add(ivdelete, 5, 1);

        enter = new Image("images/buttons/enter.png");
        iventer = new ImageView(enter);
        gpButtons.add(iventer, 6, 1);

        gameControls.add(gpNumbers,0,0);
        gameControls.add(gpButtons,0,1);

    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuNewGame=new Menu("New game",null);
        Menu menuSavegame= new Menu("Save game",null);
        Menu menuHighscores=new Menu("Highscores",null);
        Menu menuHelp = new Menu("Help",null, aboutMI, rulesMI);
        MenuBar menuBar = new MenuBar(menuFile,menuNewGame,menuSavegame,menuHighscores,menuHelp);
        setTop(menuBar);
        setCenter(gameOverview);
        setMargin(gameOverview,new Insets(10));
        gameOverview.setVgap(10);
        gameOverview.setHgap(10);
        gameOverview.setAlignment(Pos.CENTER);
        gpNumbers.setAlignment(Pos.CENTER);
        gpButtons.setAlignment(Pos.CENTER);
        gameControls.setVgap(10);
        setBottom(gameControls);
        setMargin(gameControls,new Insets(10));
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


    //buttons

    public ImageView getIv0() {
        return iv0;
    }

    public ImageView getIv1() {
        return iv1;
    }

    public ImageView getIv2() {
        return iv2;
    }

    public ImageView getIv3() {
        return iv3;
    }

    public ImageView getIv4() {
        return iv4;
    }

    public ImageView getIv5() {
        return iv5;
    }

    public ImageView getIv6() {
        return iv6;
    }

    public ImageView getIv7() {
        return iv7;
    }

    public ImageView getIv8() {
        return iv8;
    }

    public ImageView getIv9() {
        return iv9;
    }

    public ImageView getIvplus() {
        return ivplus;
    }

    public ImageView getIvminus() {
        return ivminus;
    }

    public ImageView getIvmultiply() {
        return ivmultiply;
    }

    public ImageView getIvdivide() {
        return ivdivide;
    }

    public ImageView getIvequals() {
        return ivequals;
    }

    public ImageView getIvdelete() {
        return ivdelete;
    }

    public ImageView getIventer() {
        return iventer;
    }




}

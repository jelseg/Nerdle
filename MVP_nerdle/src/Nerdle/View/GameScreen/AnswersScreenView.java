package Nerdle.View.GameScreen;

import Nerdle.View.UISettings;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class AnswersScreenView extends GridPane {

    private UISettings uiSettings;

    private List<ImageView> imageViewList;
    private Image blanco;

    public AnswersScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){
        blanco = new Image("images/darkgrey.png");
        imageViewList = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 8; column++) {
                ImageView ivBlanco = new ImageView(blanco);
                add(ivBlanco, column, row);
                imageViewList.add(ivBlanco);
            }
        }
    }

    private void layoutNodes(){
        setHgap(10);
        setVgap(10);
        for (ImageView ivBlanco : imageViewList) {
            setValignment(ivBlanco, VPos.CENTER);
        }
    }

    //getters

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

}

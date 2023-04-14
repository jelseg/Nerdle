package Nerdle.View.GameScreen;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ButtonsScreenView extends GridPane {
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

    GridPane gpNumbers=new GridPane();
    GridPane gpButtons=new GridPane();

    public ButtonsScreenView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        for (int i = 0; i <= 9; i++) {
            String imageName = "lightgray" + i + ".png";
            Image image = new Image("images/buttons/" + imageName);
            ImageView imageView = new ImageView(image);
            if (i == 0) {
                iv0 = imageView; // store the first ImageView in iv0
            }
            gpNumbers.add(imageView, i, 0);
        }

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

        add(gpNumbers,0,0);
        add(gpButtons,0,1);

    }

    private void layoutNodes() {
        setVgap(10);
        gpNumbers.setAlignment(Pos.CENTER);
        gpButtons.setAlignment(Pos.CENTER);
    }

    //getters

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

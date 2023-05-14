package Nerdle.View.MainScreen;

import Nerdle.Model.EquationCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuessesTile extends ImageView {
    //private Image image;

    public GuessesTile(){
        super();
        Image image = new Image("images/darkgrey.png");
        this.setImage(image);
    }

    public GuessesTile(EquationCharacter eqc){
        this();
        changeCharacter(eqc);
    }

    void changeCharacter( EquationCharacter eqc){

        String imagePath;


        //get path to the correct image
        if (eqc.getOperation() == EquationCharacter.Operation.EMPTY) {
            imagePath = "images/darkgrey.png";
        }
        else{
            StringBuilder builder = new StringBuilder("images/results/");

            switch(eqc.getColor()){
                case GRAY: builder.append("lightgray"); break;
                case BLACK: builder.append("black");break;
                case GREEN: builder.append("green"); break;
                case PURPLE: builder.append("purple"); break;
            }

            switch (eqc.getOperation()){
                case NUMBER: builder.append(eqc.getNumber()); break;
                case EQUALS: builder.append("_equals"); break;
                case PLUS: builder.append("_plus"); break;
                case MINUS: builder.append("_minus"); break;
                case DIVIDE: builder.append("_divide"); break;
                case MULTIPLY: builder.append("_multiply"); break;
            }

            builder.append(".png");

            imagePath = builder.toString();

            //System.out.println(imagePath);
        }
        Image image = new Image(imagePath);
        this.setImage(image);


    }
}
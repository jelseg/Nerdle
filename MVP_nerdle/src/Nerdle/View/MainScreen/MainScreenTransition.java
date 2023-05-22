package Nerdle.View.MainScreen;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainScreenTransition {
    private MainScreenView view;
    private ImageView imageView;
    private ImageView congrats;

    public MainScreenTransition(MainScreenView view) {
        this.view = view;

        imageView = new ImageView(new Image("images/confetti.gif"));
        congrats = new ImageView(new Image("images/congratulations.png"));
        view.getChildren().addAll(imageView,congrats);
    }

    public void play() {

        double targetHeight = view.getHeight() / 2;
        double targetY = (view.getHeight() - targetHeight);

        imageView.setViewport(new Rectangle2D(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight()));
        imageView.setX(view.getWidth()/2-imageView.getImage().getWidth()/2);
        imageView.setY(targetY/8);

        congrats.setViewport(new Rectangle2D(0, 0, congrats.getImage().getWidth(), congrats.getImage().getHeight()));
        congrats.setX(view.getWidth()/2-congrats.getImage().getWidth()/2);
        congrats.setLayoutY(-400);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(5), congrats);
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), congrats);
        translateTransition.setToY(targetY);

        translateTransition.play();

        translateTransition.setOnFinished(event -> {
            scaleTransition.play();
        });
    }
}



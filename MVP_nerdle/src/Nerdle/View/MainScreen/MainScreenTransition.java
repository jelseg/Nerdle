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

    public MainScreenTransition(MainScreenView view) {
        this.view = view;

        imageView = new ImageView(new Image("images/confetti.gif"));
        view.getChildren().add(imageView);
    }

    public void play() {

        double targetWidth = view.getWidth() / 2;
        double targetHeight = view.getHeight() / 2;
        double targetX = (view.getWidth() - targetWidth) / 2;
        double targetY = (view.getHeight() - targetHeight) / 2;

        imageView.setViewport(new Rectangle2D(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight()));
        imageView.setX(targetX);
        imageView.setY(targetY);

//        double targetWidth = view.getWidth() / 2;
//        double targetHeight = view.getHeight() / 2;
//        double targetX = (view.getWidth() - targetWidth) / 2;
//        double targetY = (view.getHeight() - targetHeight) / 2;
//
//        imageView.setViewport(new Rectangle2D(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight()));
//        imageView.setX(targetX);
//        imageView.setY(targetY);
//
//        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(5), imageView);
//        scaleTransition.setToX(0.5);
//        scaleTransition.setToY(0.5);
//
//        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), imageView);
//        translateTransition.setByX(-380);
//        translateTransition.setByY(-200);
//
//        RotateTransition rotateTransition = new RotateTransition();
//        rotateTransition.setNode(imageView);
//        rotateTransition.setDuration(Duration.seconds(5));
//        rotateTransition.setByAngle(360);
//        rotateTransition.setCycleCount(2);
//        rotateTransition.setInterpolator(Interpolator.LINEAR);
//
//        translateTransition.play();
//
//        translateTransition.setOnFinished(event -> {
//            scaleTransition.play();
//        });
//        scaleTransition.setOnFinished(event -> {
//            rotateTransition.play();
//        });

    }
}



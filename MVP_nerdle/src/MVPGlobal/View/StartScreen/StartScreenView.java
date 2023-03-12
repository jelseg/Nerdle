package MVPGlobal.View.StartScreen;

import MVPGlobal.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.nio.file.Files;

public class StartScreenView extends BorderPane  {

    private UISettings uiSettings;
    private Label timeDisplay;
    private ProgressBar timeProgress;
    private StartScreenTransition trans;

    public StartScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        this.timeDisplay = new Label("Loading: 0.0");
        this.timeProgress = new ProgressBar();
    }

    private void layoutNodes() {
        int ImageSize = uiSettings.getLowestRes()/5;
        BorderPane progressPane = new BorderPane();
        progressPane.setRight(this.timeProgress);
        progressPane.setLeft(this.timeDisplay);
        BorderPane.setMargin(this.timeDisplay, new Insets(uiSettings.getInsetsMargin()));
        BorderPane.setMargin(this.timeProgress, new Insets(uiSettings.getInsetsMargin()));
        ImageView centralImage;
        if (Files.exists(uiSettings.getStartScreenImagePath())) {
           try {
                centralImage = new ImageView(new Image(uiSettings.getStartScreenImagePath().toUri().toURL().toString()));
                centralImage.setPreserveRatio(true);
                centralImage.setFitHeight(ImageSize);
                centralImage.setFitWidth(ImageSize);
                centralImage.setSmooth(true);
                this.setCenter(centralImage);
            }
            catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if StartScreenImage is not available, program can continue
        }
        this.setBottom(progressPane);
    }

    Label getTimeDisplay () {return (timeDisplay);}

    ProgressBar getTimeProgress () {return (timeProgress);}

    StartScreenTransition getTransition() {return trans;}

    private void animate() {
        trans = new StartScreenTransition(this,3);
        trans.play();
    }

}

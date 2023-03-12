package MVPGlobal;

import MVPGlobal.View.StartScreen.*;
import MVPGlobal.Model.*;
import MVPGlobal.View.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import java.net.MalformedURLException;
import java.nio.file.Files;

public class MVPMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        UISettings uiSettings = new UISettings();
        MVPModel model = new MVPModel();
        StartScreenView view = new StartScreenView(uiSettings);
        StartScreenPresenter presenter = new StartScreenPresenter(model, view, uiSettings);
        Scene scene = new Scene(view);
        if (uiSettings.styleSheetAvailable()){
            try {
                scene.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setHeight(uiSettings.getLowestRes() / 4);
        primaryStage.setWidth(uiSettings.getLowestRes() / 4);
        primaryStage.setTitle(uiSettings.getApplicationName());
        if (Files.exists(uiSettings.getApplicationIconPath())) {
             try {
                 primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
             }
             catch (MalformedURLException ex) {
                 // do nothing, if toURL-conversion fails, program can continue
             }
        } else { // do nothing, if ApplicationIcon is not available, program can continue
        }
        presenter.windowsHandler();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

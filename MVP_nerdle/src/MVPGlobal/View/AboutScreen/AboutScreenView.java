package MVPGlobal.View.AboutScreen;

import java.net.MalformedURLException;
import java.nio.file.Files;
import MVPGlobal.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AboutScreenView extends BorderPane {

    private UISettings uiSettings;
    private Button okButton;

    public AboutScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        okButton = new Button("OK");
        okButton.setPrefWidth(60);
    }

    private void layoutNodes() {
        BorderPane centralPane = new BorderPane();
         if (Files.exists(uiSettings.getAboutImagePath())) {
            try {
                centralPane.setCenter(new ImageView(new Image(uiSettings.getAboutImagePath().toUri().toURL().toString())));
            }
            catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if AboutImage is not available, program can continue
        }
        VBox labelsPane = new VBox();
        labelsPane.getChildren().addAll(new Label("Developed by: Segers Jelle & Bergmans Jasper"),
                                        new Label("v1.0"));
        centralPane.setBottom(labelsPane);
        setCenter(centralPane);
        setPadding(new Insets(uiSettings.getInsetsMargin()));
        BorderPane.setAlignment(okButton, Pos.CENTER_RIGHT);
        BorderPane.setMargin(okButton, new Insets(uiSettings.getInsetsMargin(), 0, 0, 0));
        setBottom(okButton);
        setPrefWidth(uiSettings.getLowestRes() / 4);
        setPrefHeight(uiSettings.getLowestRes() / 4);
    }

    Button getBtnOk() {
        return okButton;
    }
}

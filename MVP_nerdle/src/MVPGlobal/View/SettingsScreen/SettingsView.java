package MVPGlobal.View.SettingsScreen;

import MVPGlobal.View.UISettings;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;

public class SettingsView extends BorderPane  {

    private UISettings uiSettings;
    private MenuItem exitMI;
    private TextField cssName;
    private Button cssButton;
    private Button okButton;

    public SettingsView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.cssButton = new Button("Select File");
        this.cssName = new TextField();
        this.cssName.setPrefWidth(uiSettings.getLowestRes() / 3);
        this.cssName.setText(uiSettings.getStyleSheetPath().toString());
        this.okButton = new Button("OK");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(exitMI);
        MenuBar menuBar = new MenuBar(menuFile);
        setTop(menuBar);
        HBox cssSettings = new HBox();
        cssSettings.setSpacing(uiSettings.getLowestRes() / 100);
        cssSettings.setPadding(new Insets(20));
        Label cssLabel = new Label("Style Sheet File Name:");
        cssSettings.getChildren().addAll(cssLabel, cssName, cssButton);
        this.setCenter(cssSettings);
        okButton.setAlignment(Pos.BOTTOM_RIGHT);
        this.setBottom(okButton);
    }

    MenuItem getExitItem() {return exitMI;}
    Button getCssButton () {return cssButton;}
    TextField getCssName () {return cssName;}
    Button getOkButton () {return okButton;}
}

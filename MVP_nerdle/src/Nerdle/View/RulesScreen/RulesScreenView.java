package Nerdle.View.RulesScreen;

import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class RulesScreenView extends BorderPane{

    private UISettings uiSettings;
    private TextArea rulesText;
    private Button okButton;

    public RulesScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        rulesText = new TextArea("test");
        okButton = new Button("OK");
        okButton.setPrefWidth(60);
    }

    private void layoutNodes() {
        setCenter(rulesText);
        rulesText.setPrefWidth(Double.MAX_VALUE);
        rulesText.setPrefHeight(Double.MAX_VALUE);
        rulesText.setWrapText(true);
        rulesText.setFont(Font.font("Arial", 12));
        rulesText.setEditable(false);
        setPadding(new Insets(uiSettings.getInsetsMargin()));
        BorderPane.setAlignment(okButton, Pos.CENTER_RIGHT);
        BorderPane.setMargin(okButton, new Insets(uiSettings.getInsetsMargin(), 0, 0, 0));
        setBottom(okButton);
        setPrefWidth(uiSettings.getLowestRes() / 4);
        setPrefHeight(uiSettings.getLowestRes() / 4);
    }

    TextArea getRulesText() {return rulesText;}

    Button getBtnOk() {
        return okButton;
    }
}

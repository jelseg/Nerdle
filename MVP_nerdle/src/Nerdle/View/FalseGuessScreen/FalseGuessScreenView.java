package Nerdle.View.FalseGuessScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;


public class FalseGuessScreenView extends BorderPane {
    private Label label;
    private Button button;

    public FalseGuessScreenView() {
        getStylesheets().add("stylesheets/Buttons.css");
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){
        this.label=new Label("Your guess did not did not compute");
        this.button=new Button("Modify current guess");
    }

    private void layoutNodes(){
        this.setCenter(label);
        setAlignment(button, Pos.CENTER);
        this.setBottom(button);
    }

    public Button getOkButton() {
        return button;
    }
}
